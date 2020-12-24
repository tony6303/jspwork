import java.util.List;

import dao.DeptDao;
import model.Dept;

public class MainApp {
	
	public static void main(String[] args) {
//		추가(9);
//		삭제(1);
//		찾기(10);
//		Dept dept = 찾기(10);
		List<Dept> listDept = DeptDao.전체찾기();
	}
}
