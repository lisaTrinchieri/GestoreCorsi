package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.db.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	
	public Model() {
		this.corsoDao = new CorsoDAO();
	}
	
	public List<Corso> getCorsiByPeriodo(Integer pd) {
		return corsoDao.getCorsiByPeriodo(pd);
		
	}
	
	public Map<Corso, Integer> getIscrittiByPeriodo(Integer pd) {
		return corsoDao.getIscrittiByPeriodo(pd);
	}
	
	public List<String> getCodiceCorsi() {
		return corsoDao.getCodiceCorsi();
	}
	
	public List<Studente> getStudentiByIndirizzo(String codins)  {
		return this.studenteDao.getStudentiByIndirizzo(codins);
	}
	
	public List<Studente> getStudentiByCorso(String codins)  {
		return this.studenteDao.getStudentiByCorso(codins);
	}
}
