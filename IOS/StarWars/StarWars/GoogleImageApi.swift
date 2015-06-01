//
//  GoogleImageApi.swift
//  StarWars
//
//  Created by Le Tyrant Mathieu on 01/06/2015.
//  Copyright (c) 2015 BangBang. All rights reserved.
//

import Foundation
import SwiftyJSON
import Alamofire

struct GoogleImageApi {
    
    /*
    * Base URL API
    */
    static let baseUrl = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=";
    
    /*
    ******
    * Get picture of the people
    ******
    */
    static func getPicture(name: String, response: (JSON) -> ()){
        
        
        let urlAPI = NSURL(string: GoogleImageApi.baseUrl + name.replace(" ", replacement: ""));
                
        Alamofire.request(.GET, urlAPI!, parameters: nil, encoding: .JSON)
            .responseJSON { (_, _, data, error) in
                if error == nil {
                    let jsonData = JSON(data!)
                    response(jsonData["responseData"]["results"])
                }else{
                    println("\(error)")
                }
        }
        
    }
    
}