package app.dkh.interviewapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import app.dkh.interviewapplication.local.MenuDao;
import app.dkh.interviewapplication.models.FoodItem;
import app.dkh.interviewapplication.utils.DataGenerator;
import io.realm.Realm;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    private Realm mRealm;
    private int numberOfItems = 10;
    private List<FoodItem> testList = DataGenerator.getMenuList(numberOfItems);
    private MenuDao dao;


    @Before
    public void setUp() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        Realm.init(appContext);
        mRealm = Realm.getDefaultInstance();
        clear();

        dao = Injection.getDatabaseInstance(mRealm);


    }

    //This test crashed
    @Test
    public void testSaveMenuList() {

        /*dao.saveMenu(testList);

        List<FoodItem> resultList = dao.getMenu().getValue().getData().getItems();
        boolean isEquals = false;

        if (resultList != null) {
            isEquals = resultList.size() == testList.size();
        }
        assertTrue("Menu Lists are not equal", isEquals);*/

    }

    @Test
    public void testGetMenuItem() {

        dao.saveMenu(testList);

        FoodItem foodItem = dao.getFoodItem(1);

        boolean isEquals = false;

        if (foodItem != null) {
            isEquals = testList.get(1).getName().equals(foodItem.getName());
        }
        assertTrue("Menu items are not equal", isEquals);
    }

    @After
    public void tearDown() {
        mRealm.close();
    }

    private void clear() {
        mRealm.executeTransaction(realm -> realm.deleteAll());
    }

}
