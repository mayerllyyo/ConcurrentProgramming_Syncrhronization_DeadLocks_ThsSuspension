/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.lab_1.blacklistvalidator;

import java.util.List;


/**
 *
 * @author hcadavid
 */
public class Main {
    
    public static void main(String a[]){
        edu.eci.lab_1.blacklistvalidator.HostBlackListsValidator hblv=new edu.eci.lab_1.blacklistvalidator.HostBlackListsValidator();
        List<Integer> blackListOcurrences=hblv.checkHost("200.24.34.55", 5);
        System.out.println("The host was found in the following blacklists:"+blackListOcurrences);
        
    }
    
}
