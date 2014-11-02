//
//  FENewsVC.m
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FENewsVC.h"
#import <HMSegmentedControl/HMSegmentedControl.h>
#import "FENewsTableViewCell.h"
#import "FEWarringTableViewCell.h"
#import "FEWarringResponse.h"
#import "FEWebServiceManager.h"

@interface FENewsVC ()<UITableViewDataSource,UITableViewDelegate>

@property (nonatomic, strong) UITableView *newstable;
@property (nonatomic, strong) UITableView *warringtable;
@property (nonatomic, strong) NSMutableArray *newsList;
@property (nonatomic, strong) NSMutableArray *warringList;

@end

@implementation FENewsVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = FEString(@"HOME_PAGE");
        if (SYSTEM_VERSION_UP7) {
            UITabBarItem *tabitem = [[UITabBarItem alloc] initWithTitle:FEString(@"HOME_PAGE") image:[UIImage imageNamed:@"tabbar_home"] selectedImage:nil];
            self.tabBarItem = tabitem;
        }else{
            [self.tabBarItem setFinishedSelectedImage:[UIImage imageNamed:@"tabbar_home_select"] withFinishedUnselectedImage:[UIImage imageNamed:@"tabbar_home"]];
        }
        _newsList = [NSMutableArray arrayWithObjects:@"新闻1",@"新闻2",@"新闻3",@"新闻4",@"新闻5", nil];
        _warringList = [NSMutableArray arrayWithObjects:@"warring 1",@"warring1",@"warring3",@"warring4", nil];
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self initUI];
    
    //request news
    [self requestNews];
}

-(void)initUI{
    //新闻和告警切换segment
    HMSegmentedControl *segmentedControl = [[HMSegmentedControl alloc] initWithSectionTitles:@[FEString(@"NEWS"), FEString(@"WARRING")]];
    segmentedControl.selectedTextColor = [UIColor orangeColor];
//    segmentedControl.backgroundColor = [UIColor lightGrayColor];
    segmentedControl.selectionIndicatorColor = [UIColor orangeColor];
    segmentedControl.frame = CGRectMake(0, 0, 320, 44);
    segmentedControl.selectionStyle = HMSegmentedControlSelectionStyleFullWidthStripe;
    segmentedControl.selectionIndicatorLocation = HMSegmentedControlSelectionIndicatorLocationDown;
    segmentedControl.selectionIndicatorHeight = 3;
    segmentedControl.autoresizingMask = UIViewAutoresizingFlexibleRightMargin | UIViewAutoresizingFlexibleWidth;
    [segmentedControl addTarget:self action:@selector(segmentedControlChangedValue:) forControlEvents:UIControlEventValueChanged];
    [self.view addSubview:segmentedControl];
    
    //新闻table
    UITableView *news = [[UITableView alloc] initWithFrame:CGRectMake(0, segmentedControl.bounds.size.height, self.view.bounds.size.width, self.view.bounds.size.height - segmentedControl.bounds.size.height) style:UITableViewStylePlain];
    news.delegate = self;
    news.dataSource = self;
    news.autoresizingMask = UIViewAutoresizingFlexibleHeight;
    [self.view addSubview:news];
    self.newstable = news;
    //为table添加空白view
    UIView *view = [UIView new];
    view.backgroundColor = [UIColor clearColor];
    [news setTableFooterView:view];

    
    //告警table
    UITableView *warring = [[UITableView alloc] initWithFrame:CGRectMake(0, segmentedControl.bounds.size.height, self.view.bounds.size.width, self.view.bounds.size.height - segmentedControl.bounds.size.height) style:UITableViewStylePlain];
    [warring setHidden:YES];
    warring.delegate = self;
    warring.dataSource = self;
    warring.autoresizingMask = UIViewAutoresizingFlexibleHeight;
    [self.view addSubview:warring];
    self.warringtable = warring;
    
    UIView *view1 = [UIView new];
    view1.backgroundColor = [UIColor clearColor];
    [warring setTableFooterView:view];
    
}

-(void)requestNews{
    [self displayHUD:FEString(@"LOADING...")];
    FEPage *page = [[FEPage alloc] initWithPageSize:10 currentPage:0 count:1];
    FEAttribute *attr = [[FEAttribute alloc] initWithAttrName:@"" value:@""];
    FENewsRequest *news = [[FENewsRequest alloc] initWithPage:page filter:@[attr.dictionary]];
    [[FEWebServiceManager sharedInstance] news:news response:^(NSError *error, FEDataNew *user) {
        [self hideHUD:YES];
        if (error) {
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"提示" message:[NSString stringWithFormat:@"%@",error.userInfo] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles: nil];
            [alert show];
        }
        
    }];
}

-(void)segmentedControlChangedValue:(HMSegmentedControl *)control{
    if (control.selectedSegmentIndex == 0) {
        if (self.newstable.isHidden == NO) {
            return;
        }else{
            [UIView animateWithDuration:0.2
                                  delay:0
                                options:UIViewAnimationOptionTransitionFlipFromLeft
                             animations:^(void){
                                 self.newstable.hidden = NO;
                                 self.warringtable.hidden = YES;
                             }
                             completion:nil];
        }
    }else if(control.selectedSegmentIndex == 1){
        if (self.warringtable.isHidden == NO) {
            return;
        }else{
            [UIView animateWithDuration:0.2
                                  delay:0
                                options:UIViewAnimationOptionTransitionFlipFromLeft
                             animations:^(void){
                                 self.newstable.hidden = YES;
                                 self.warringtable.hidden = NO;
                             }
                             completion:nil];
        }
    }
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    if (tableView == self.newstable) {
        static NSString *identifier = @"cell";
        FENewsTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
        if (!cell) {
            cell = [[FENewsTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:identifier];
        }
//        NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
        
        cell.titleLabel.text = self.newsList[indexPath.row];
        cell.timeLabel.text = @"00/00/00/00";
        return cell;
    }else if(tableView == self.warringtable){
        static NSString *identifier = @"cell";
        FEWarringTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
        if (!cell) {
            cell = [[FEWarringTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:identifier];
        }
        return cell;
    }
    return nil;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    if (tableView == self.newstable) {
        return self.newsList.count;
    }else if(tableView == self.warringtable){
        return self.warringList.count;
    }
    return 0;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 50;
}

#pragma mark - UITableViewDelegate
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    if (tableView == self.warringtable) {
        FEWarringResponse *response = [FEWarringResponse new];
        response.hidesBottomBarWhenPushed = YES;
        [self.navigationController pushViewController:response animated:YES];
    }
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
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
