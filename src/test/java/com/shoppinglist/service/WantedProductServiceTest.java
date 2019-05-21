package com.shoppinglist.service;

import com.shoppinglist.configuration.DatabaseConfiguration;
import com.shoppinglist.exception.NoWantedProductFoundException;
import com.shoppinglist.model.database.Category;
import com.shoppinglist.model.database.WantedProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.shoppinglist.service.DatabaseInsertedData.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
@Transactional
public class WantedProductServiceTest {
    @Autowired
    private WantedProductService wantedProductService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testGetWantedProductById_shouldBeSuccessful() {
        WantedProduct wantedProduct = wantedProductService.getWantedProductById(INSERTED_WANTED_PRODUCT_1.getId());
        assertThat(wantedProduct, is(INSERTED_WANTED_PRODUCT_1));
    }

    @Test(expected = NoWantedProductFoundException.class)
    public void testGetWantedProductByIdWhenNoProductWithWantedIdExists_shouldThrowExceptions() {
        wantedProductService.getWantedProductById(0L);
    }

    @Test
    public void testGetWantedProductsByClubId_shouldBeSuccessful() {
        List<WantedProduct> wantedProducts = wantedProductService.getWantedProductsByClubId(INSERTED_CLUB_1.getId());
        assertTrue(wantedProducts.contains(INSERTED_WANTED_PRODUCT_2));
        assertTrue(wantedProducts.contains(INSERTED_WANTED_PRODUCT_1));
    }

    @Test
    public void testGetWantedProductsByClubIdAndBoughtTrue_shouldBeSuccessful() {
        List<WantedProduct> wantedProducts = wantedProductService.getWantedProductsByClubIdAndBoughtTrue(INSERTED_CLUB_1.getId());
        assertTrue(!wantedProducts.contains(INSERTED_WANTED_PRODUCT_1));
        assertTrue(wantedProducts.contains(INSERTED_WANTED_PRODUCT_2));
    }

    @Test
    public void testGetWantedProductsByClubIdAndBoughtFalse_shouldBeSuccessful() {
        List<WantedProduct> wantedProducts = wantedProductService.getWantedProductsByClubIdAndBoughtFalse(INSERTED_CLUB_1.getId());
        assertTrue(wantedProducts.contains(INSERTED_WANTED_PRODUCT_1));
        assertTrue(!wantedProducts.contains(INSERTED_WANTED_PRODUCT_2));
    }

    @Test
    public void testMarkAsBought_shouldBeSuccessful() {
        wantedProductService.markAsBought(INSERTED_WANTED_PRODUCT_1.getId());
        WantedProduct wantedProduct = wantedProductService.getWantedProductById(INSERTED_WANTED_PRODUCT_1.getId());
        assertTrue(wantedProduct.isBought());
    }

    @Test
    public void testAddWantedProduct_shouldBeSuccessful() {
        wantedProductService.addWantedProduct(INSERTED_CLUB_1.getId(), new WantedProduct(null, "newProduct", "newDesc", Category.FOOD, false, false), null);
        assertThat(NR_OF_INITIAL_WANTED_PRODUCTS + 1, is(JdbcTestUtils.countRowsInTable(jdbcTemplate, WANTED_PRODUCT_TABLE)));
    }
}
