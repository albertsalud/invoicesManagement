package tk.daudecinc.balance.utils.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.daudecinc.balance.controllers.dto.ExpenseDTO;
import tk.daudecinc.balance.controllers.dto.InvoiceDTO;
import tk.daudecinc.balance.model.services.ExpenseService;
import tk.daudecinc.balance.model.services.InvoiceService;
import tk.daudecinc.balance.utils.interfaces.BalanceDocument;
import tk.daudecinc.balance.utils.services.BalanceService;

@Service
public class BalanceServiceImpl implements BalanceService{
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<BalanceDocument> findBalanceDocumentsByYear(Integer year) {
		List<BalanceDocument> documents = new ArrayList<>();
		documents.addAll(
				invoiceService.findAllByYear(year).stream()
					.map(invoice -> {
						return modelMapper.map(invoice, InvoiceDTO.class);
					})
					.collect(Collectors.toList())
				);
		
		documents.addAll(
				expenseService.findAllByYear(year).stream()
					.map(expense -> {
						return modelMapper.map(expense, ExpenseDTO.class);
					})
					.collect(Collectors.toList())
				);
		
		return documents;
	}

}
