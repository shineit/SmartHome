//
//  FEENavgationController.m
//  SmartHome
//
//  Created by Seven on 15-4-17.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEENavgationController.h"
#import "FEEWarringVC.h"

@interface FEENavgationController ()

@end

@implementation FEENavgationController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}

-(void)toAlarmSegue:(FECompany *)company{
    [self performSegueWithIdentifier:@"toAlarmSegue" sender:company];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
    if([segue.identifier isEqualToString:@"toAlarmSegue"]){
        FEEWarringVC *vc = segue.destinationViewController;
        vc.company = sender;
    }
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
