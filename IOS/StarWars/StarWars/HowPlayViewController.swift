//
//  HowPlayViewController.swift
//  StarWars
//
//  Created by Le Tyrant Mathieu on 01/06/2015.
//  Copyright (c) 2015 BangBang. All rights reserved.
//

import UIKit

class HowPlayViewController: UIViewController {
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(true);
        navigationController?.navigationBar.hidden = true; // for navigation bar hide
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}
