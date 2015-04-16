//
//  FEKnowLedgeDetailVC.m
//  SmartHome
//
//  Created by Seven on 15-4-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEKnowLedgeDetailVC.h"
#import "FEKnowledge.h"
#import "UIColor+Theme.h"

@interface FEKnowLedgeDetailVC ()
@property (strong, nonatomic) IBOutlet UILabel *titleLabel;
@property (strong, nonatomic) IBOutlet UITextView *contentText;

@end

@implementation FEKnowLedgeDetailVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"消防常识";
    self.titleLabel.text = self.knowledge.title;
    self.contentText.text = self.knowledge.content;
    self.contentText.layer.borderColor = [UIColor ThemeColor].CGColor;
    self.contentText.layer.borderWidth = 1.0f;
    self.contentText.layer.cornerRadius = 4.0f;
    self.contentText.layer.masksToBounds = YES;
    
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
