//
//  ViewController.swift
//  StarWars
//
//  Created by Le Tyrant Mathieu on 29/05/2015.
//  Copyright (c) 2015 BangBang. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var BtnPlay: UIButton!
    @IBOutlet weak var BtnHowPlay: UIButton!
    @IBOutlet weak var BtnYourResults: UIButton!
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(true);
        navigationController?.navigationBar.hidden = true; // for navigation bar hide
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}

