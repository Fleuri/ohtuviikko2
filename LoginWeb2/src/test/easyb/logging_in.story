import ohtu.*
import ohtu.authentication.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can log in with valid username/password-combination'

scenario "user can login with correct password", {
    given 'login selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080");
        element = driver.findElement(By.linkText("login"));       
        element.click();       
    }

    when 'a valid username and password are given', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        element.submit();
    }
 
    then 'user will be logged in to system', {
        driver.getPageSource().contains("Welcome to Ohtu Application!").shouldBe true
    }
}

scenario "user can not login with incorrect password", {
    given 'command login selected', {
driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080");
        element = driver.findElement(By.linkText("login"));       
        element.click();       
    }
    when 'a valid username and incorrect password are given', {
 element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akke");
        element = driver.findElement(By.name("login"));
        element.submit();
}
    then 'user will not be logged in to system', {
    driver.getPageSource().contains("wrong username or password").shouldBe true
}
}

scenario "nonexistent user can not login to system", {
    given 'command login selected', {
driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080");
        element = driver.findElement(By.linkText("login"));       
        element.click();       
    }
    when 'a nonexistent username and some password are given', {
element = driver.findElement(By.name("username"));
        element.sendKeys("peka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        element.submit();
}
    then 'user will not be logged in to system', {
    driver.getPageSource().contains("wrong username or password").shouldBe true
}
}

scenario "user can create a new account if there are no existing accounts with the same name", {
    given ' Registration selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();
}
    when 'User gives a proper name and password', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("lauri");
        element = driver.findElement(By.name("password"));
        element.sendKeys("jeejeejee666");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("jeejeejee666");
        element = driver.findElement(By.name("add"));
        element.submit();
        }
    then 'user will be logged registered in to system', {
        driver.getPageSource().contains("info for newbie").shouldBe true
        }
}