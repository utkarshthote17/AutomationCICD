
@tag
Feature: submit order
  I want to use this template for my feature file
  
  Background:
  Given I landed on E-Commerce Page

 
  @Regression
  Scenario Outline: Submit order in E-commerce App
    Given Login to App using username<email> and password<password>
    When I add product<product> to cart
    And Checkout product<product> and submit order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation page

    Examples: 
      | email  									| password 						 | product  			 |
      | utkarshthote1@gmail.com | Shoprahulshetty123@# | ADIDAS ORIGINAL |

      
     