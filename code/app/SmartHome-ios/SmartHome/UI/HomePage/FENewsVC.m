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


@interface FENewsVC ()<UITableViewDataSource,UITableViewDelegate,FETableViewRefreshDelegate>{
    BOOL _isNewsResquested;
    BOOL _isWarringRequested;
    NSInteger _page;
}

@property (nonatomic, strong) FETableView *newstable;
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
    _page = 0;
    [self initUI];
    
    //request news
//    [self requestNews];
}

-(void)initUI{

    //新闻table
    FETableView *news = [[FETableView alloc] initWithFrame:self.view.bounds style:UITableViewStylePlain];
    news.delegate = self;
    news.dataSource = self;
    news.autoresizingMask = UIViewAutoresizingFlexibleHeight;
    [self.view addSubview:news];
    self.newstable = news;
    self.newstable.loadMore = YES;
    self.newstable.pullRefresh = YES;
    self.newstable.refreshDelegate = self;
    [self.newstable addRefresh];
    //为table添加空白view
    UIView *view = [UIView new];
    view.backgroundColor = [UIColor clearColor];
    [news setTableFooterView:view];
    
    [self.newstable startPullDownRefreshing];

}

//request news
-(void)requestNews{
    _isNewsResquested = YES;
    FEPage *page = [[FEPage alloc] initWithPageSize:10 currentPage:_page count:1];
    FEAttribute *attr = [[FEAttribute alloc] initWithAttrName:@"" value:@""];
    FENewsRequest *news = [[FENewsRequest alloc] initWithPage:page filter:@[attr]];
    
    __weak typeof(self) weakself = self;
    
    [[FEWebServiceManager sharedInstance] news:news response:^(NSError *error, FENewsResponse *news) {
        if (!error && news.result.errorCode.integerValue == 0){
            if (news.newsList.count) {
                _page++;
                [weakself.newsList addObjectsFromArray:news.newsList];
                [weakself.newstable reloadData];
                [weakself.newstable endLoadMoreRefresing];
            }else{
                [weakself.newstable endMoreOverWithMessage:@"没有更多了"];
            }
            
        }else{
            [weakself.newstable endLoadMoreRefresing];
        }
        [weakself.newstable endPullDownRefreshing];
    }];
}

#pragma mark - FETableViewRefreshDelegate
-(void)beginLoadMore:(FETableView *)tableview{
    [self requestNews];
}

-(void)beginPullRefresh:(FETableView *)tableview{
    _page = 0;
    [self.newsList removeAllObjects];
    [self.newstable reloadData];
    [self requestNews];
    
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
