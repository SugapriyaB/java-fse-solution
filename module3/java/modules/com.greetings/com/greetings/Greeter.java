package com.greetings;

import com.utils.string.StringUtils;
import com.utils.time.TimeUtils;

public class Greeter {
    public static void main(String[] args) {
        String name = args.length > 0 ? args[0] : "World";
        
        // Use string utils
        String capitalizedName = StringUtils.capitalize(name);
        String reversedName = StringUtils.reverse(name);
        
        // Use time utils
        String currentTime = TimeUtils.getCurrentTime();
        
        // Display greetings
        System.out.println("Module-based Greeting Application");
        System.out.println("-------------------------------");
        System.out.println("Current time: " + currentTime);
        System.out.println("Normal greeting: Hello, " + capitalizedName + "!");
        System.out.println("Reverse greeting: Hello, " + reversedName + "!");
    }
} 