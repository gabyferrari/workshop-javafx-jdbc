package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {

	//troca a implementaçao mockada de baixo pela chamada do DepartementDao
	private DepartmentDao dao = DaoFactory.createDepartmentDao(); //que vai no banco de dados e recupera os departamentos
 	
	public List<Department> findAll() {
		return dao.findAll(); //vai no banco e busca os departamentos para a gente
	}
}
