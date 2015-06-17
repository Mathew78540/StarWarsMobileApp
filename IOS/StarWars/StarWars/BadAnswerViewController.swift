//
//  ViewController.swift
//  StarWars
//
//  Created by Le Tyrant Mathieu on 29/05/2015.
//  Copyright (c) 2015 BangBang. All rights reserved.
//

import UIKit

class BadAnswerViewController: UIViewController {
    
    // PARAMS IN SEGUET
    var name:String     = "";
    var image:String    = "";
    var score:Int       = 0;
    
    // OUTLETS
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var scoreLabel: UILabel!
    @IBOutlet weak var nameLabel: UILabel!
    
    // DETECT VALUE IN SEGUET AND ADD IT IN THE VIEW
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(true);
        navigationController?.navigationBar.hidden = true;
        
        var url     = NSURL(string: image);
        let data    = NSData(contentsOfURL: url!)
        
        scoreLabel.text = String(score);
        nameLabel.text  = name;
        
        if let url = NSURL(string: image) {
            if let data = NSData(contentsOfURL: url){
                imageView.contentMode = UIViewContentMode.ScaleAspectFit
                imageView.image = UIImage(data: data)
            }
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}

