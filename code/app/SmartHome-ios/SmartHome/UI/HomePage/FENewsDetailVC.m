//
//  FENewsDetailVC.m
//  SmartHome
//
//  Created by Seven on 14-11-10.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FENewsDetailVC.h"
#import "FENews.h"

@interface FENewsDetailVC ()

@end

@implementation FENewsDetailVC


-(id)initWithNews:(FENews *)news{
    self = [super init];
    if (self) {
        _news = news;
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = FEString(@"News");
    [self initUI];
}

-(void)initUI{
    UITextView *textview = [[UITextView alloc] initWithFrame:self.view.bounds];
    textview.editable = NO;
    textview.text = self.news.content;
    [self.view addSubview:textview];
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
