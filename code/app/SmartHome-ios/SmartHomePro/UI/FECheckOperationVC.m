//
//  FECheckOperationVC.m
//  SmartHome
//
//  Created by Seven on 15-4-8.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECheckOperationVC.h"

@interface FECheckOperationVC ()
@property (strong, nonatomic) IBOutlet UIButton *normalButon;
@property (strong, nonatomic) IBOutlet UIButton *exButton;
@property (strong, nonatomic) IBOutlet UIButton *nonOperation;
@property (strong, nonatomic) IBOutlet UIButton *uploadButton;
@property (strong, nonatomic) IBOutlet UITextView *descriptionText;
@property (strong, nonatomic) IBOutlet UIView *imageContent;

@end

@implementation FECheckOperationVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self selectButton:self.nonOperation];
    self.title = @"日常巡检";
}
- (IBAction)normal:(id)sender {
    [self selectButton:sender];
}
- (IBAction)exception:(id)sender {
    [self selectButton:sender];
}
- (IBAction)nonOperation:(id)sender {
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
