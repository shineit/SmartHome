//
//  FEEmptyVC.m
//  SmartHome
//
//  Created by Seven on 15-1-27.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEEmptyVC.h"

@interface FEEmptyVC ()

@end

@implementation FEEmptyVC

-(id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        self.title = FEString(@"HOME_PAGE");
        UITabBarItem *tabitem = [[UITabBarItem alloc] initWithTitle:FEString(@"HOME_PAGE") image:[UIImage imageNamed:@"tabbar_home"] selectedImage:nil];
        self.tabBarItem = tabitem;
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
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
