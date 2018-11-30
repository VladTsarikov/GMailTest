package framework.webdriver.waitings;

import framework.enums.LogType;
import framework.utils.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

class Condition {

    static ExpectedCondition<Boolean> JQueryLoadCondition(){
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
                }
                catch (Exception e) {
                    Logger.log(LogType.DEBUG, e);
                    return true;
                }
            }
        };
        return jQueryLoad;
    }

    static ExpectedCondition<Boolean> documentNotActiveCondition(){
        ExpectedCondition<Boolean> javascriptDone = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                try{
                    return  ((Boolean) ((JavascriptExecutor) d).executeAsyncScript(
                            " var callback =arguments[arguments.length - 1]; " +
                                    " var count=42; " +
                                    " setTimeout( collect, 0);" +
                                    " function collect() { " +
                                    " if(count-->0) { "+
                                    " setTimeout( collect, 0); " +
                                    " } "+
                                    " else {callback(" +
                                    "    true" +
                                    " );}"+
                                    " } "
                    ));
                }catch (Exception e) {
                    Logger.log(LogType.DEBUG, e);
                    return Boolean.FALSE;
                }
            }
        };
        return javascriptDone;
    }

    static ExpectedCondition<Boolean> JStoLoadCondition() {
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jsLoad;
    }
}