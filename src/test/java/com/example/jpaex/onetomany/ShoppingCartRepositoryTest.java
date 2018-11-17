package com.example.jpaex.onetomany;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class ShoppingCartRepositoryTest {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void shouldCreateShoppingCart() {
        //Given
        Discount discount1 = Discount.builder().discountName("discountName1").percentage(0.5f).build();
        Discount discount2 = Discount.builder().discountName("discountName2").percentage(0.2f).build();
        Item item1 = Item.builder().itemName("itemName1").build();
        Item item2 = Item.builder().itemName("itemName2").build();
        LocalDateTime createdTime = now();
        ShoppingCart shoppingCart = ShoppingCart.builder().createdTime(createdTime).customerName("customerName").build();

        List<Item> items = List.of(item1, item2);
        List<Discount> discounts = List.of(discount1, discount2);
        items.stream().forEach(item -> item.setShoppingCart(shoppingCart));
        discounts.stream().forEach(discount -> discount.setShoppingCart(shoppingCart));
        shoppingCart.setDiscounts(discounts);
        shoppingCart.setItems(items);

        //When
        shoppingCartRepository.save(shoppingCart);

        //Then
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
        assertThat(shoppingCarts).hasSize(1);
        assertThat(shoppingCarts).contains(shoppingCart);
        ShoppingCart found = shoppingCarts.get(0);
        assertThat(found.getDiscounts()).containsExactlyInAnyOrder(discount1, discount2);
        assertThat(found.getItems()).containsExactlyInAnyOrder(item1, item2);
    }

}