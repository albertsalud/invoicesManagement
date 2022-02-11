package tk.daudecinc.balance.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tk.daudecinc.balance.controllers.dto.InvoiceDTO;
import tk.daudecinc.balance.model.entities.Invoice;
import tk.daudecinc.balance.model.services.InvoiceService;
import tk.daudecinc.balance.utils.ControllersUtils;
import tk.daudecinc.balance.utils.YearHolder;
import tk.daudecinc.balance.utils.services.UploadDocumentService;

@Controller
@RequestMapping("/invoices")
public class InvoicesController {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ControllersUtils controllersUtils;
	
	@Autowired
	private UploadDocumentService uploadDocumentService;
	
	@Autowired
	private YearHolder yearHolder;

	@GetMapping(value = {"/", ""})
	public String listInvoices(@RequestParam(required = false) Integer year,
			Model model) {
		yearHolder.setYear(year);
		
		List<Invoice> invoices = invoiceService.findAllByYear(yearHolder.getYear());
		
		model.addAttribute("invoicesList", 
			invoices.stream()
				.map(invoice -> {
					return mapper.map(invoice, InvoiceDTO.class);
				})
				.collect(Collectors.toList())
				);
		
		controllersUtils.addConfigurationsToModel(model);
		
		return "invoices_list";
	}
	
	@GetMapping(value="/new")
	public String newInvoiceForm(Model model) {
		InvoiceDTO dto = new InvoiceDTO();
		
		return goToInvoicesForm(model, dto);
	}

	private String goToInvoicesForm(Model model, InvoiceDTO dto) {
		model.addAttribute("invoiceFormDTO", dto);
		return "invoices_form";
	}
	
	@PostMapping("/save")
	public String saveInvoice(@Valid @ModelAttribute InvoiceDTO dto, 
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			return this.goToInvoicesForm(model, dto);
		}
		
		Invoice invoiceToSave = mapper.map(dto, Invoice.class);
		dto.setYear(invoiceToSave.getYear());
		
		String newDocumentName = uploadDocumentService.uploadDocument(dto);
		if(newDocumentName != null) {
			invoiceToSave.setDocumentName(newDocumentName);
		}
		
		invoiceService.save(invoiceToSave);
		
		return this.listInvoices(null, model);
	}
	
	@GetMapping("/{id}")
	public String getInvoice(@PathVariable(name = "id") Long invoiceId, Model model) {
		Invoice requestedInvoice = invoiceService.findById(invoiceId);
		
		InvoiceDTO dto = null;
		if(requestedInvoice == null) {
			model.addAttribute("message", "Requested invoice doesn't exist!");
			dto = new InvoiceDTO();
		
		} else {
			dto = mapper.map(requestedInvoice, InvoiceDTO.class);
		}
		
		return goToInvoicesForm(model, dto);
		
	}
	
	@GetMapping("/delete")
	public String deleteInvoice(@RequestParam(name = "invoice", required = true) Long invoiceId, Model model) {
		invoiceService.deleteInvoice(invoiceId);
		
		return this.listInvoices(null, model);
	}
	
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> downloadInvoices(@RequestParam(required = true) Integer year) {
		HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename("invoices_" + year + ".zip").build().toString()); 
        return ResponseEntity.ok().headers(httpHeaders).body(
        		invoiceService.downloadAllInvoices(year).toByteArray());
	}
	
}
