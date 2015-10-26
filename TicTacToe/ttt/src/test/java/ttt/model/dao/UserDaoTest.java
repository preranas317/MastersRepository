/*package ttt.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test(groups = "UserDaoTest")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    UserDao userDao;

    @Test
public void getPlayer() {
		
		assert userDao.getPlayer("cysun")!=null;
	}
 @Test   
public void getGamesAgainstAI() {
		
		assert userDao.getGamesAgainstAI(userDao.getPlayer("cysun")).size()>2 ;
		assert userDao.getGamesAgainstAI(userDao.getPlayer("cysun")).get(1).getResult()=="win" ;
		assert userDao.getGamesAgainstAI(userDao.getPlayer("cysun")).get(2).getResult()=="loss";
		
		
	}
@Test
public void getSavedGames() {
	
	assert userDao.getSavedGames(userDao.getPlayer("cysun")) != null;
	
} 
   
}*/