//
//  SwApi.swift
//  StarWars
//
//  Created by Le Tyrant Mathieu on 01/06/2015.
//  Copyright (c) 2015 BangBang. All rights reserved.
//

import Foundation
import SwiftyJSON
import Alamofire

struct SwApi {
    
    /*
     * Base URL API
     */
    static let baseUrl = "http://swapi.co/api/";
    
    /*
     ******
     * Get Information of one People
     ******
     */
    static func People(id: Int, response: (JSON) -> ()){
        
        let urlAPI = NSURL(string: SwApi.baseUrl + "people/" + String(id));
        
        Alamofire.request(.GET, urlAPI!, parameters: nil, encoding: .JSON)
            .responseJSON { (_, _, data, error) in
                if error == nil {
                    let jsonData = JSON(data!)
                    response(jsonData)
                }else{
                    println("\(error)")
                }
        }
    }
    
}