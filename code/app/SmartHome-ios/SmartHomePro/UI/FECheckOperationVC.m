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
#import "FEUploadImageVC.h"
#import "FEImageDeleteRequest.h"
#import "FEMemoryCache.h"
#import "FEWebServiceManager.h"
#import <ZBUtilities/UIImage+LogN.h>
#import "UIColor+Theme.h"
#import "UIColor+Hex.h"
#import "FECustomer.h"

@interface FECheckOperationVC ()<FEUpLoadImageVCDelegate>
@property (strong, nonatomic) IBOutlet UIButton *normalButon;
@property (strong, nonatomic) IBOutlet UIButton *exButton;
@property (strong, nonatomic) IBOutlet UIButton *nonOperation;
@property (strong, nonatomic) IBOutlet UIButton *uploadButton;
@property (strong, nonatomic) IBOutlet UITextView *descriptionText;
@property (strong, nonatomic) IBOutlet UIView *imageContent;
@property (strong, nonatomic) FECheckLog *checkLog;
@property (strong, nonatomic) IBOutlet UIImageView *uploadImageView;

@end

@implementation FECheckOperationVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self selectButton:self.nonOperation];
    self.title = @"日常巡检";
    [self.uploadButton setBackgroundImage:[UIImage imageFromColor:[UIColor colorWithHex:0x48B805]] forState:UIControlStateNormal];
    _checkLog = [FECheckLog new];
    self.checkLog.companyName = self.company.companyName;
    self.checkLog.companyID = self.company.companyID;
    self.checkLog.checkItem = self.checkItem.itemName;
//    self.checkLog.checkItemID = self.checkItem.itemID;
    self.checkLog.checker = [FEMemoryCache sharedInstance].customer.customerName;
    
}
- (IBAction)normal:(id)sender {
    self.checkLog.checkResult = @(1);
    self.descriptionText.text = @"";
    [self deleteImage];
    self.uploadImageView.image = nil;
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

-(void)deleteImage{
    if (self.checkLog.abnormalPic) {
        self.checkLog.abnormalPic = nil;
        FEImageDeleteRequest *rdata = [[FEImageDeleteRequest alloc] initWithUid:[FEMemoryCache sharedInstance].user.userID imageName:self.checkLog.abnormalPic];
        [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FEBaseResponse class] response:^(NSError *error, id response) {
            
        }];
    }
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
    self.checkLog.abnormalDesp = self.descriptionText.text;
    [_checkLogs setObject:self.checkLog forKey:self.checkItem.itemID];
    [self.navigationController popViewControllerAnimated:YES];
//    [[FEMemoryCache sharedInstance] addCheckLog:self.checkLog];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
    if (sender == self.uploadButton) {
        FEUploadImageVC *vc = segue.destinationViewController;
        vc.delegate = self;
        
    }
}

#pragma mark - FEUploadImageVCDelegate
-(void)didUpLoadImage:(UIImage *)image withName:(NSString *)imageName{
    self.uploadImageView.image = image;
    self.checkLog.abnormalPic = imageName;
}


@end
