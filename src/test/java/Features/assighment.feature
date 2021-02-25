Feature: Final Assignment
  @Working @DayOr @Not
  Scenario: Checking Weather Working Day or not
    Given Get The Current Date
    When Check for the day Weather Working Day or Not
    Then Return  The Day
@StringSort
  Scenario Outline: Arranging the Given string into Alphabetical Order
    Given Take <input> As a String
    When arrange the all characters of the string int Alphabetical order
    Then Print the String in Console
    Examples:
      | input      |
      | Helloworld |
@RandomDate
  Scenario Outline: Ganerate Random Date within Specified Range
    Given Take <Range> as input
    When Generate Random Date with in given Range
    Then Return The Date
    Examples:
      |Range    |
      |3        |
  @RandomDatesWithOutWorkingDays
  Scenario Outline: Ganerate Random Date within Specified Range
    Given Accept <range> as input
    When Produce Random Date with in given Range
    Then Return The RandomDate
    Examples:
      |range    |
      |2        |
    @SelectCurrentorFutureDate
    Scenario Outline: Select Given Date if it is current or future date
      Given Accept <date> ad input in YYYY/MM/DD format
      When check weather given date is future date or current date or not
      And Navigate to <url>
      Then Switch to Frame
      And Select the given month
      And select date
      Examples:
        |date           |url                             |
        |2021-02-27     |https://jqueryui.com/datepicker/|
      @PrintWebTableValues
      Scenario Outline: Print All the values from WebTable
        Given Initializ The Driver
        When Navigate the WebPage to <URL>
        Then Print The Values of WebTable
        Examples:
        |URL                                                |
        | https://www.w3schools.com/html/html_tables.asp    |
        @PrintFirstGivenNNumber
        Scenario Outline: Print First Given N Numbers By Replacing 5 Multiples with fizz and 7 Multiples Buzz and 5&7 common multiples with Fizzbuzz
          Given Give <n> as input
          When replace number values with Fizz or buzz if they are multiples or 5 or 7
          Then print the values to the console
          Examples:
          |n|
          |40|

