package com.automationexercise.pages.shop;

import com.automationexercise.models.CartItemDetails;
import com.automationexercise.pages.base.BasePage;
import com.automationexercise.pages.payment.CheckoutPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private By emptyCartHeader = By.id("empty_cart");

    private By cartRow = By.cssSelector("#cart_info_table tbody tr");
    private By itemName = By.cssSelector(".cart_description h4");
    private By itemPrice = By.cssSelector(".cart_price p");
    private By itemQuantity = By.cssSelector(".cart_quantity button");
    private By itemTotalPrice = By.cssSelector(".cart_total_price");
    private By itemDeleteButton = By.cssSelector(".cart_quantity_delete");

    private By checkoutButton = By.cssSelector(".check_out");
    private By checkoutModal = By.id("checkoutModal");
    private By registerLoginButton = By.cssSelector("#checkoutModal .modal-body a[href='/logout']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage clickDeleteCartItem(int index) {
        List<WebElement> items = findAll(cartRow);
        WebElement targetItem = items.get(index);
        WebElement deleteButton = findNested(targetItem, itemDeleteButton);
        click(deleteButton);
        return this;
    }

    public CartPage waitForCartVisibility() {
        waitForVisibilityOf(cartRow);
        return this;
    }

    public CartPage waitForCartClear() {
        waitForInvisibilityOf(cartRow);
        return this;
    }

    public CheckoutPage clickCheckoutButton() {
        click(checkoutButton);
        return new CheckoutPage(driver);
    }

    // Getter Methods
    public CartItemDetails getCartItemDetails(int index) {
        List<WebElement> items = findAll(cartRow);
        WebElement targetItem = items.get(index);

        String name = getText(findNested(targetItem, itemName));
        String price = getText(findNested(targetItem, itemPrice));
        String quantity = getText(findNested(targetItem, itemQuantity));
        String totalPrice = getText(findNested(targetItem, itemTotalPrice));
        return new CartItemDetails(name, price, quantity, totalPrice);
    }

    public List<CartItemDetails> getAllCartItemDetails() {
        List<WebElement> items = findAll(cartRow);
        List<CartItemDetails> allItems = new ArrayList<>();

        for (WebElement item : items) {
            String name = getText(findNested(item, itemName));
            String price = getText(findNested(item, itemPrice));
            String quantity = getText(findNested(item, itemQuantity));
            String totalPrice = getText(findNested(item, itemTotalPrice));

            allItems.add(new CartItemDetails(name, price, quantity, totalPrice));
        }
        return allItems;
    }

    public String getCartItemQuantity(int index) {
        List<WebElement> items = findAll(cartRow);
        WebElement targetItem = items.get(index);
        return getText(findNested(targetItem, itemQuantity));
    }

    public String getEmptyCartHeader() {
        return getText(emptyCartHeader);
    }
}
