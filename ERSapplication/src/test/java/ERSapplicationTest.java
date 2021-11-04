import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ERS.Service.Service;
import com.ERS.dao.ReimbursementDaoImpl;
import com.ERS.dao.UserDaoImpl;
import com.ERS.model.Reimbursement;
import com.ERS.model.User;

public class ERSapplicationTest {
	
	private UserDaoImpl uDao;
	private ReimbursementDaoImpl rDao;
	private Service testService = new Service(rDao,uDao);
	private User testUser;
	private Reimbursement testReimb;
	
	@BeforeAll
	public void setUp() throws Exception {
		//MockitoAnnotations.initMocks(this);
		testService = new Service(rDao, uDao);
		testUser = new User("User1", "Password1", "T", "U", "soniaben@rev.com", 3);
		//when(uDao.getUserByUsername("User1")).thenReturn(testUser);
	
	}
	@Test
	public void testGetUserSuccess() {
		//assertEquals(testService.login("User1", "Password1"));	
	}
	}
