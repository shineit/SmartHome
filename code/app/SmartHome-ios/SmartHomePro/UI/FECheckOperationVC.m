//
//  FECheckOperationVC.m
//  SmartHome
//
//  Created by Seven on 15-4-8.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECheckOperationVC.h"
#import "FECheckLog.h"
#import "FECompany.h"
#import "FECheckItem.h"
#import "FEMemoryCache.h"

@interface FECheckOperationVC ()
@property (strong, nonatomic) IBOutlet UIButton *normalButon;
@property (strong, nonatomic) IBOutlet UIButton *exButton;
@property (strong, nonatomic) IBOutlet UIButton *nonOperation;
@property (strong, nonatomic) IBOutlet UIButton *uploadButton;
@property (strong, nonatomic) IBOutlet UITextView *descriptionText;
@property (strong, nonatomic) IBOutlet UIView *imageContent;
@property (strong, nonatomic) FECheckLog *checkLog;

@end

@implementation FECheckOperationVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self selectButton:self.nonOperation];
    self.title = @"日常巡检";
    _checkLog = [FECheckLog new];
    self.checkLog.companyID = self.company.companyID;
    self.checkLog.checkItem = self.checkItem.itemID.stringValue;
    
}
- (IBAction)normal:(id)sender {
    self.checkLog.checkResult = @(1);
    [self selectButton:sender];
}
- (IBAction)exception:(id)sender {
    self.checkLog.checkResult = @(2);
    [self selectButton:sender];
}
- (IBAction)nonOperation:(id)sender {
    self.checkLog.checkResult = @(0);
    [self selectButton:sender];
    
}

-(void)selectButton:(UIButton *)btn{
    self.normalButon.selected = NO;
    self.nonOperation.selected = NO;
    self.exButton.selected = NO;
    btn.selected = YES;
    if (btn == self.exButton) {
        self.descriptionText.hidden = NO;
        self.imageContent.hidden = NO;
        self.uploadButton.hidden = NO;
    }else{
        self.descriptionText.hidden = YES;
        self.imageContent.hidden = YES;
        self.uploadButton.hidden = YES;
    }
}

- (IBAction)upload:(id)sender {
    
}

- (IBAction)save:(id)sender {
    [[FEMemoryCache sharedInstance] addCheckLog:self.checkLog];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
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
