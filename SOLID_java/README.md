Exercise: Applying SOLID principles
-----------------------------------

The Code has the following behaviour:    

* Books can be added to the basket    
* Basket calculates the total price with no discounts    
* Basket calculates discounts for IT and Travel books    
* Tests are provided for the current functionality    

**Requirement:** Give 20% discount for each Fantasy book when more than one is in the basket.

**Proposed refactoring:**

* Extract discount offers from Basket
* Create basket informing the discount offers available
* Transform the books List in a First Class Collection, moving respective behavior to it.

**Implementing the requirement**

* Test-Drive the Fantasy Book discount offer
* Create the basket adding the Fantasy discount offer to it.

**Lesson:**

* New features can be slid into the code almost without change 
* Changes while adding new functionality are small and localised
* Changes happen in the main (application assembly) and not in the domain
