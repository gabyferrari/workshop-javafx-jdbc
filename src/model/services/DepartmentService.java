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
	
	public void saveOrUpdate(Department obj) { //ver se tem q inserir um novo departament no banco ou atualizar um ja existente
		if (obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	//para remover um departamento do banco de dados
	public void remove(Department obj) {
		dao.deleteById(obj.getId());
	}
}
