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
#import "FEWarringDetailVC.h"
#import "FEWebServiceManager.h"
#import "FENewsRequest.h"
#import "FENewsResponse.h"
#import "FENews.h"
#import "FEResult.h"
#import "FEHistoryAlarmRequest.h"
#import "FECoreDataHandler.h"
#import "AppDelegate.h"
#import "CDUser.h"
#import "FEHistoryAlarmResponse.h"
#import "FENewsDetailVC.h"


@interface FENewsVC ()<UITableViewDataSource,UITableViewDelegate>{
    BOOL _isNewsResquested;
    BOOL _isWarringRequested;
}

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
        _newsList = [NSMutableArray new];
        _warringList = [NSMutableArray new];
        
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
    [self requestHistoryWarring];
}

-(void)initUI{
    //新闻和告警切换segment
//    HMSegmentedControl *segmentedControl = [[HMSegmentedControl alloc] initWithSectionTitles:@[FEString(@"HOME_NEWS"), FEString(@"HOME_WARRING")]];
////    segmentedControl.sectionSelectedImages = @[[[UIImage imageFromColor:[UIColor whiteColor]] imageScaledToSize:CGSizeMake(self.view.bounds.size.width / 2, 44)],[[UIImage imageFromColor:[UIColor whiteColor]] imageScaledToSize:CGSizeMake(self.view.bounds.size.width / 2, 44)]];
//    segmentedControl.selectedTextColor = [UIColor orangeColor];
//    segmentedControl.backgroundColor = [UIColor whiteColor];//FEColor(229, 229, 229, 1);//[UIColor lightGrayColor];
//    segmentedControl.selectionIndicatorColor = [UIColor orangeColor];
//    segmentedControl.frame = CGRectMake(0, 0, self.view.bounds.size.width, 44);
//    segmentedControl.selectionStyle = HMSegmentedControlSelectionStyleFullWidthStripe;
//    segmentedControl.selectionIndicatorLocation = HMSegmentedControlSelectionIndicatorLocationDown;
//    segmentedControl.selectionIndicatorHeight = 3;
//    segmentedControl.autoresizingMask = UIViewAutoresizingFlexibleRightMargin | UIViewAutoresizingFlexibleWidth;
//    [segmentedControl addTarget:self action:@selector(segmentedControlChangedValue:) forControlEvents:UIControlEventValueChanged];
//    [self.view addSubview:segmentedControl];
//    segmentedControl.selectedSegmentIndex = 1;
    
    //新闻table
    UITableView *news = [[UITableView alloc] initWithFrame:self.view.bounds style:UITableViewStylePlain];
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
//    UITableView *warring = [[UITableView alloc] initWithFrame:CGRectMake(0, segmentedControl.bounds.size.height, self.view.bounds.size.width, self.view.bounds.size.height - segmentedControl.bounds.size.height) style:UITableViewStylePlain];
//    [warring setHidden:YES];
//    warring.delegate = self;
//    warring.dataSource = self;
//    warring.autoresizingMask = UIViewAutoresizingFlexibleHeight;
//    [self.view addSubview:warring];
//    self.warringtable = warring;
//    
//    UIView *view1 = [UIView new];
//    view1.backgroundColor = [UIColor clearColor];
//    [warring setTableFooterView:view];
//    
//    self.newstable.hidden = YES;
//    self.warringtable.hidden = NO;
}

//request news
-(void)requestNews{
    _isNewsResquested = YES;
    FEPage *page = [[FEPage alloc] initWithPageSize:10 currentPage:0 count:1];
    FEAttribute *attr = [[FEAttribute alloc] initWithAttrName:@"" value:@""];
    FENewsRequest *news = [[FENewsRequest alloc] initWithPage:page filter:@[attr.dictionary]];
    
    __weak typeof(self) weakself = self;
    
    [[FEWebServiceManager sharedInstance] news:news response:^(NSError *error, FENewsResponse *news) {
        if (!error && news.result.errorCode.integerValue == 0){
            [weakself.newsList removeAllObjects];
            [weakself.newsList addObjectsFromArray:news.newsList];
            [weakself.newstable reloadData];
        }
        
    }];
}

//request alarm list
-(void)requestHistoryWarring{
    _isWarringRequested = YES;
    FEPage *page = [[FEPage alloc] initWithPageSize:0 currentPage:0 count:0];
    FEAttribute *attr = [[FEAttribute alloc] initWithAttrName:@"" value:@""];
    FEHistoryAlarmRequest *hdata = [[FEHistoryAlarmRequest alloc] initWithUserID:FELoginUser.userid page:page attributes:@[attr]];
    
     __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] historyAlarmList:hdata reponse:^(NSError *error, FEHistoryAlarmResponse *response) {
        if (!error && response.result.errorCode.integerValue == 0){
            [weakself.warringList removeAllObjects];
            [weakself.warringList addObjectsFromArray:response.alarmList];
            [weakself.warringtable reloadData];
        }
    }];
    
}

-(void)segmentedControlChangedValue:(HMSegmentedControl *)control{
    if (control.selectedSegmentIndex == 0) {
        if (self.newstable.isHidden == NO) {
            return;
        }else{
            if (!_isNewsResquested) {
                [self requestNews];
            }
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
            if (!_isWarringRequested) {
                [self requestHistoryWarring];
            }
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
        FENews *fnew = self.newsList[indexPath.row];
        cell.titleLabel.text = fnew.title;
        cell.timeLabel.text = [[NSDate dateWithTimeIntervalSince1970:(fnew.date.longLongValue / 1000)] defaultFormat];
        return cell;
    }else if(tableView == self.warringtable){
        static NSString *identifier = @"cell";
        FEWarringTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
        if (!cell) {
            cell = [[FEWarringTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:identifier];
        }
        [cell configWithAlarm:_warringList[indexPath.row]];
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
        FEWarringDetailVC *response = [[FEWarringDetailVC alloc] initWithAlarm:_warringList[indexPath.row]];
        response.hidesBottomBarWhenPushed = YES;
        [self.navigationController pushViewController:response animated:YES];
    }else if(tableView == self.newstable){
        FENews *news = self.newsList[indexPath.row];
        FENewsDetailVC *detail = [[FENewsDetailVC alloc] initWithNews:news];
        detail.hidesBottomBarWhenPushed = YES;
        [self.navigationController pushViewController:detail animated:YES];
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
