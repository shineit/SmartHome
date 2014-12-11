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
    
    FELabel *titleLabel = [[FELabel alloc] initWithFrame:CGRectMake(0, 0, 250, 30)];
    titleLabel.textAlignment = NSTextAlignmentCenter;
    titleLabel.font = [UIFont appFontWithSize:20];
    titleLabel.center = CGPointMake(self.view.bounds.size.width / 2, 30);
    [self.view addSubview:titleLabel];
    titleLabel.text = self.news.title;
    
    FELabel *authorLabel = [[FELabel alloc] initWithFrame:CGRectMake(self.view.bounds.size.width - 100, titleLabel.frame.origin.y + titleLabel.bounds.size.height + 5, 80, 20)];
    authorLabel.font = [UIFont appFontWithSize:12];
    authorLabel.textAlignment = NSTextAlignmentRight;
    [self.view addSubview:authorLabel];
    authorLabel.text = self.news.author;
    
    UITextView *textview = [[UITextView alloc] initWithFrame:CGRectMake(0, authorLabel.frame.origin.y + authorLabel.bounds.size.height + 10, self.view.bounds.size.width, self.view.bounds.size.height - (authorLabel.frame.origin.y + authorLabel.bounds.size.height + 10))];
    textview.autoresizingMask = UIViewAutoresizingFlexibleHeight;
    textview.editable = NO;
    textview.font = [UIFont appFontWithSize:16];
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
