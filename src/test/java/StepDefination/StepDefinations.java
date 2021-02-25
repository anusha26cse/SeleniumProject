package StepDefination;

//import PageObjects.DatePick;
import PageObjects.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import resources.BaseClass;

import javax.xml.validation.Validator;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//import io.cucumber.java.en.When;

public class StepDefinations extends BaseClass{
    WebDriver driver;
    LocalDate date;
    Calendar c;
    @Given("^Get The Current Date$")
    public void get_the_current_date() throws Throwable {
        System.out.println("check if the current date is a working day");
        date = LocalDate.now();
      c= Calendar.getInstance();
    }
    int day;
    @When("^Check for the day Weather Working Day or Not$")
    public void check_for_the_day_weather_working_day_or_not() throws Throwable {
        day = c.get(Calendar.DAY_OF_WEEK);
    }
    @Then("^Return  The Day$")
    public void return_the_day() throws Throwable {
        if((day==1)||(day==7)){
            System.out.println(date+" Today is Holiday");
        }
        else{
            System.out.println(date+" Today is Working Day");
        }
        System.out.println("");
    }

    String str;
    @Given("^Take (.+) As a String$")
    public void take_as_a_string(String input) throws Throwable {
        System.out.println("Arrange characters of String in alphabetical order");
            str=input;
            System.out.println("given string is "+str);
    }
    @When("^arrange the all characters of the string int Alphabetical order$")
    public void arrange_the_all_characters_of_the_string_int_alphabetical_order() throws Throwable {
        str =
                Stream.of(str.split("")).sorted(Comparator.comparingInt(o->Character.toLowerCase(o.charAt(0)))).distinct().collect(Collectors.joining());
    }
    @Then("^Print the String in Console$")
    public void print_the_string_in_console() throws Throwable {
        System.out.println("String After arranging in Assending order nd removing duplicates "+str);
        System.out.println("");
    }

    int weeks;
    long start;
    long end;
    long randomEpochDay;
    LocalDate d;
    @Given("^Take (.+) as input$")
    public void take_as_input(String range) throws Throwable {
        System.out.println("generate any random future date within specified range of weeks.");
        weeks = Integer.parseInt(range);
    }
    @When("^Generate Random Date with in given Range$")
    public void generate_random_date_with_in_given_range() throws Throwable {
        LocalDate startDate = LocalDate.now().plusDays(1);
        start = startDate.toEpochDay();
        LocalDate endDate = startDate.plusWeeks(weeks);
        end = endDate.toEpochDay();
        randomEpochDay= ThreadLocalRandom.current().longs(start,end).findAny().getAsLong();
    }
    @Then("^Return The Date$")
    public void return_the_date() throws Throwable {
        System.out.println("Randome Date with in "+weeks+" weeks: "+LocalDate.ofEpochDay(randomEpochDay));
        System.out.println("");
    }


    @Given("^Accept (.+) as input$")
    public void accept_as_input(String range) throws Throwable {
        System.out.println("generate any random future date of week day within specified range of weeks o");
        weeks=Integer.parseInt(range);
        LocalDate startDate=LocalDate.now().plusDays(1);
        start=startDate.toEpochDay();
        LocalDate endDate=startDate.plusWeeks(weeks);
        end=endDate.toEpochDay();
    }

    @When("^Produce Random Date with in given Range$")
    public void produce_random_date_with_in_given_range() throws Throwable {
        RandomDates rd=new RandomDates();
        while (true) {
            d = rd.Dategeneration(start, end);
            Date Rdate = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
            int Rday = rd.getDayNumber(Rdate);
            if (Rday != 1 && Rday != 7) {
                break;
            }
        }

    }
    @Then("^Return The RandomDate$")
    public void return_the_randomdate() throws Throwable {
System.out.println(d+" "+d.getDayOfWeek());
System.out.println("");
    }


    String reqMonthYear;
    LocalDate givenDate;
    String rDate;
   DatePick dp;
    int reqDate;
    @Given("^Accept (.+) ad input in YYYY/MM/DD format$")
    public void accept_ad_input_in_yyyymmdd_format(String date) throws Throwable {
        System.out.println("select current / future date in date picker element");
        driver=initializerDriver();
        rDate=date;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        givenDate= LocalDate.parse(rDate);
        reqDate = givenDate.getDayOfMonth();
        reqMonthYear=String.valueOf(givenDate.getMonth()).concat(" ").concat(String.valueOf(givenDate.getYear()));
    }

    @When("^check weather given date is future date or current date or not$")
    public void check_weather_given_date_is_future_date_or_current_date_or_not() throws Throwable {
        boolean tday=LocalDate.parse(rDate).isBefore(LocalDate.now());
        Assert.assertFalse(tday);
    }
    @And("^Navigate to (.+)$")
    public void navigate_to(String url) throws Throwable {
        driver.get(url);
    }
    @Then("^Switch to Frame$")
    public void switch_to_frame() throws Throwable {
        dp=new DatePick(driver);
        driver.switchTo().frame(dp.getFrame());
        dp.getDatePicker().click();
    }
    @And("^Select the given month$")
    public void navigate_to_given_month() throws Throwable {
        while (true) {
            if (dp.getTitle().getText().equalsIgnoreCase(reqMonthYear))
                break;
            else
                dp.getNavigation().click();
        }
    }

    @And("^select date$")
    public void select_date() throws Throwable {
        int count=dp.getDates().size();
        for(int i=0;i<count;i++){
            if(dp.getDates().get(i).getText().equalsIgnoreCase(String.valueOf(reqDate))){
                dp.getDates().get(i).click();
            }
        }
System.out.println();
    }
    @Given("^Initializ The Driver$")
    public void initializ_the_driver() throws Throwable {
        System.out.println("print all the values present in a web table");
driver=initializerDriver();
    }

    @When("^Navigate the WebPage to (.+)$")
    public void navigate_the_webpage_to(String url) throws Throwable {
      driver.get(url);
    }
WebTables wt;
    @Then("^Print The Values of WebTable$")
    public void print_the_values_of_webtable() throws Throwable {
        wt = new WebTables(driver);
       // System.out.println(wt.colomn());
        for(int i=0;i<wt.colomn();i++){
            String header=wt.ColomnHeader().get(i).getText();
            System.out.format("%-35s",header);
        }
        System.out.println("");
wt.TableData();
System.out.println();
    }
    int range;
    @Given("^Give (.+) as input$")
    public void give_as_input(String n) throws Throwable {
        System.out.println("print first given N numbers in console. If the number is multiple of 5, it should print fizz. If the number is multiple of 7, it should print buzz. If the number is mutliple of both 5 and 7, it should print fizzbuzz.");
    range=Integer.parseInt(n);
    }
    ArrayList<String> values = new ArrayList<>();
    @When("^replace number values with Fizz or buzz if they are multiples or 5 or 7$")
    public void replace_number_values_with_fizz_or_buzz_if_they_are_multiples_or_5_or_7() throws Throwable {
for(Integer i = 1; i<=range; i++){
    if(i%35==0)
        values.add("FizzBuzz");
    else if(i%5==0)
        values.add("fizz");
    else if(i%7==0)
        values.add("Buzz");

    else
        values.add(String.valueOf(i));
}
    }

    @Then("^print the values to the console$")
    public void print_the_values_to_the_console() throws Throwable {
for(String val:values){
    System.out.println(val);
}
    }
}
