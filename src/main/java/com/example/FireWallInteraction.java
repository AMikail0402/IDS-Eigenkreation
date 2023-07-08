package com.example;

import java.util.concurrent.TimeUnit;

import com.profesorfalken.jpowershell.PowerShell;

public class FireWallInteraction {
    
        public static void main(String[] args) throws InterruptedException{
            String command = "Get-Process";
                System.out.println(PowerShell.executeSingleCommand(command).getCommandOutput());
                TimeUnit.SECONDS.sleep(1);
                PowerShell.executeSingleCommand("cls");

        }

}
