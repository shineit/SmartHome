//
//  FESettingVC.m
//  SmartHome
//
//  Created by Seven on 14-10-20.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FESettingVC.h"
#import "FEServiceListVC.h"

@interface FESettingVC ()<UITableViewDataSource,UITableViewDelegate>{
    NSArray *_settingItem;
    NSArray *_settingIvocation;
}

@property (nonatomic, strong) UITableView *settingTable;

@end

@implementation FESettingVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = FEString(@"SETTING");
        
        _settingItem = @[FEString(@"SERVICE"),FEString(@"ABOUT NEWS"),FEString(@"ABOUT US"),FEString(@"FADE BACK"),FEString(@"RATE US"),FEString(@"PROFILE")];
        _settingIvocation = @[[self invocation:@selector(service)],[self invocation:@selector(aboutnews)],[self invocation:@selector(aboutus)],[self invocation:@selector(fadeback)],[self invocation:@selector(rateus)],[self invocation:@selector(profile)]];
        
    }
    return self;
}

-(NSInvocation *)invocation:(SEL)selector{
    
    NSMethodSignature *sig=[self methodSignatureForSelector:selector];
    NSInvocation *invocation = [NSInvocation invocationWithMethodSignature:sig];
    [invocation setSelector:selector];
    [invocation setTarget:self];
//    [invocation retainArguments];
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

-(void)fadeback{
    NSLog(@"反馈");
}

-(void)rateus{
    NSLog(@"为我们评分");
}

-(void)profile{
    NSLog(@"Profile");
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    static NSString *identifier = @"cell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
    if (!cell) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:identifier];
    }
    cell.textLabel.text = _settingItem[indexPath.row];
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return _settingItem.count;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 50;
}

#pragma mark - UITableViewDelegate
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    NSInvocation *invocation = _settingIvocation[indexPath.row];
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
