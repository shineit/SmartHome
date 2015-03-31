//
//  FESettingVC.m
//  SmartHome
//
//  Created by Seven on 14-10-20.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FESettingVC.h"
#import "FEServiceListVC.h"
#import "FEProfileVC.h"
#import "FESettingItemCell.h"
#import "FERate.h"
#import "FECommonDefine.h"

#define _TITLE  @"title"
#define _IMAGE  @"image"
#define _FUNCTION  @"function"

@interface FESettingVC ()<UITableViewDataSource,UITableViewDelegate>{
    NSArray *_settingItem;
}

@property (nonatomic, strong) UITableView *settingTable;

@end

@implementation FESettingVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = kString(@"SETTING");
        UITabBarItem *tabitem = [[UITabBarItem alloc] initWithTitle:kString(@"SETTING") image:[UIImage imageNamed:@"tabbar_setting"] selectedImage:nil];
        self.tabBarItem = tabitem;
        
        _settingItem = @[
                        @[
                          @{_TITLE : kString(@"PROFILE"),_IMAGE : [UIImage imageNamed:@"setting_profile"],_FUNCTION : [self invocation:@selector(profile)]}
                          ],
                        @[
                          @{_TITLE : kString(@"ABOUT_US"), _IMAGE : [UIImage imageNamed:@"setting_aboutus"], _FUNCTION : [self invocation:@selector(aboutus)]},
                          @{_TITLE : kString(@"RATE_US"), _IMAGE : [UIImage imageNamed:@"setting_rating"], _FUNCTION : [self invocation:@selector(rateus)]}
                          ],
                        @[
                          @{_TITLE : kString(@"SERVICE"), _IMAGE : [UIImage imageNamed:@"setting_service"], _FUNCTION : [self invocation:@selector(service)]},
                          @{_TITLE : kString(@"ABOUT_NEWS"), _IMAGE : [UIImage imageNamed:@"setting_news"], _FUNCTION : [self invocation:@selector(aboutnews)]},
                          @{_TITLE : kString(@"FEED_BACK"), _IMAGE : [UIImage imageNamed:@"setting_feedback"], _FUNCTION : [self invocation:@selector(feedback)]}
                          ]
                        ];
    }
    return self;
}

-(NSInvocation *)invocation:(SEL)selector{
    
    NSMethodSignature *sig=[self methodSignatureForSelector:selector];
    NSInvocation *invocation = [NSInvocation invocationWithMethodSignature:sig];
    [invocation setSelector:selector];
    [invocation setTarget:self];
    return invocation;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self initUI];
}

-(void)initUI{
    UITableView *table = [[UITableView alloc] initWithFrame:self.view.bounds style:UITableViewStyleGrouped];
    table.dataSource = self;
    table.delegate = self;
    table.autoresizingMask = UIViewAutoresizingFlexibleHeight;
    [self.view addSubview:table];
    self.settingTable = table;
    
    
}

//table select index opreation
-(void)service{
    FEServiceListVC *svc = [FEServiceListVC new];
    svc.hidesBottomBarWhenPushed = YES;
    [self.navigationController pushViewController:svc animated:YES];
}

-(void)aboutnews{
    NSLog(@"新功能");
}

-(void)aboutus{
    NSLog(@"关于我们");
}

-(void)feedback{
    NSLog(@"反馈");
}

-(void)rateus{
    [[FERate sharedFERate] rate];
}

-(void)profile{
    FEProfileVC *profile = [FEProfileVC new];
    profile.hidesBottomBarWhenPushed = YES;
    [self.navigationController pushViewController:profile animated:YES];
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    static NSString *identifier = @"cell";
    FESettingItemCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
    if (!cell) {
        cell = [[FESettingItemCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:identifier];
    }
    cell.titleLabel.text = _settingItem[indexPath.section][indexPath.row][_TITLE];
    cell.headImage.image = _settingItem[indexPath.section][indexPath.row][_IMAGE];
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return ((NSArray *)_settingItem[section]).count;
}

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return _settingItem.count;
}


-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 50;
}

#pragma mark - UITableViewDelegate
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    NSInvocation *invocation = _settingItem[indexPath.section][indexPath.row][_FUNCTION];
    [invocation invoke];
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)dealloc{
    
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
