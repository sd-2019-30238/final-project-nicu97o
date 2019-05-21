package com.shoppinglist.service;

import com.shoppinglist.configuration.DatabaseConfiguration;
import com.shoppinglist.exception.NoProductConstraintFoundException;
import com.shoppinglist.model.database.ProductConstraint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import static com.shoppinglist.service.DatabaseInsertedData.INSERTED_PRODUCT_CONSTRAINT_1;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
@Transactional
public class ProductConstraintServiceTest {
    @Autowired
    private ProductConstraintService productConstraintService;

    @Test
    public void testGetProductConstraintById_shouldBeSuccessful() {
        ProductConstraint productConstraint = productConstraintService.getProductConstraintById(INSERTED_PRODUCT_CONSTRAINT_1.getId());
        assertThat(productConstraint, is(INSERTED_PRODUCT_CONSTRAINT_1));
    }

    @Test(expected = NoProductConstraintFoundException.class)
    public void testGetProductConstraintByIdWhenNoConstraintWithWantedIdExists_shouldThrowException() {
        productConstraintService.getProductConstraintById(0L);
    }
}
