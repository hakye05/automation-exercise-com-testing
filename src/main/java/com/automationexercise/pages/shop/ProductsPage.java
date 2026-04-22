package com.automationexercise.pages.shop;

import com.automationexercise.models.ProductCardDetails;
import com.automationexercise.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    private By productsHeader = By.cssSelector(".features_items h2.title");

    private By searchProductBar = By.id("search_product");
    private By searchButton = By.id("submit_search");

    private By productCard = By.cssSelector(".product-image-wrapper");
    private By productName = By.cssSelector(".productinfo p");
    private By productPrice = By.cssSelector(".productinfo h2");
    private By addToCartButton = By.cssSelector(".productinfo .add-to-cart");
    private By viewProductButton = By.cssSelector(".choose a[href*='/product_details/']");

    private By continueModal = By.id("cartModal");
    private By continueShoppingButton = By.cssSelector("[data-dismiss='modal']");
    private By viewCartButton = By.cssSelector("#cartModal .modal-body a");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public ProductsPage enterSearchWord(String word) {
        type(searchProductBar, word);
        return this;
    }

    public ProductsPage clickSearchButton() {
        click(searchButton);
        return this;
    }

    public ProductsPage clickAddToCart(int index) {
        List<WebElement> products = findAll(productCard);
        WebElement targetProduct = products.get(index);

        WebElement cartButton = findNested(targetProduct, addToCartButton);
        scrollToElement(cartButton);
        click(cartButton);
        return this;
    }

    public ProductsPage clickAddToCart(int... indices) {
        List<WebElement> products = findAll(productCard);
        int indicesLength = indices.length;

        for (int i = 0; i < indicesLength; i++) {
            WebElement targetProduct = products.get(indices[i]);
            WebElement cartButton = findNested(targetProduct, addToCartButton);
            scrollToElement(cartButton);
            click(cartButton);

            // Dismiss to chain actions
            if (i < indicesLength - 1) {
                dismissViewCartModal();
            }
        }
        return this;
    }

    public ProductDetailsPage clickViewProductDetails(int index) {
        List<WebElement> products = findAll(productCard);
        WebElement targetProduct = products.get(index);

        WebElement viewDetailsButton = findNested(targetProduct, viewProductButton);
        scrollToElement(viewDetailsButton);
        click(viewDetailsButton);
        return new ProductDetailsPage(driver);
    }

    public ProductsPage dismissViewCartModal() {
        waitForVisibilityOf(continueModal);
        click(continueShoppingButton);
        return this;
    }

    public CartPage acceptViewCartModal() {
        waitForVisibilityOf(continueModal);
        clickAvoidingVignette(viewCartButton);
        return new CartPage(driver);
    }

    // Getter Methods
    public ProductCardDetails getProductCardDetails(int index) {
        List<WebElement> products = findAll(productCard);
        WebElement targetProduct = products.get(index);

        String name = getText(findNested(targetProduct, productName));
        String price = getText(findNested(targetProduct, productPrice));
        return new ProductCardDetails(name, price);
    }

    public List<ProductCardDetails> getProductCardDetails(int... indices) {
        List<WebElement> products = findAll(productCard);
        List<ProductCardDetails> allProducts = new ArrayList<>();

        for (int index : indices) {
            WebElement targetProduct = products.get(index);
            String name = getText(findNested(targetProduct, productName));
            String price = getText(findNested(targetProduct, productPrice));

            allProducts.add(new ProductCardDetails(name, price));
        }
        return allProducts;
    }

    public List<ProductCardDetails> getAllProductCardDetails() {
        List<WebElement> products = findAll(productCard);
        List<ProductCardDetails> allProducts = new ArrayList<>();

        for (WebElement product : products) {
            String name = getText(findNested(product, productName));
            String price = getText(findNested(product, productPrice));

            allProducts.add(new ProductCardDetails(name, price));
        }
        return allProducts;
    }

    public List<String> getAllProductNames() {
        List<WebElement> products = findAll(productCard);
        List<String> allProductNames = new ArrayList<>();

        for (WebElement product : products) {
            String name = getText(findNested(product, productName));

            allProductNames.add(name);
        }
        return allProductNames;
    }

    public String getProductsHeader() {
        return getText(productsHeader);
    }
}
