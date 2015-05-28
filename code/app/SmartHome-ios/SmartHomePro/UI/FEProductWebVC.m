//
//  FEProductWebVC.m
//  SmartHome
//
//  Created by Seven on 15-5-28.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEProductWebVC.h"

@interface FEProductWebVC ()
@property (strong, nonatomic) IBOutlet UIWebView *webView;

@end

@implementation FEProductWebVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"产品查询";
    [self.webView loadRequest:[NSURLRequest requestWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"http://114.112.48.163/lableFind.jsp?isFirst=isNotFirst&start=0&curPageNum=null&queryValue=1&recordFactoryId=&lableCode=%@",self.productID]]]];
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
