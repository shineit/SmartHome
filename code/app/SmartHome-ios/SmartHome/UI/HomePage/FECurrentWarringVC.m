//
//  FECurrentWarringVC.m
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECurrentWarringVC.h"
#import "FEWebServiceManager.h"
#import "FEHistoryAlarmRequest.h"
#import "FEHistoryAlarmResponse.h"
#import "AppDelegate.h"
#import "FECoreDataHandler.h"
#import "CDUser.h"
#import "FEWarringTableViewCell.h"
#import "FEWarringDetailVC.h"


@interface FECurrentWarringVC ()

@property (nonatomic, strong) NSMutableArray *warringList;

@end

@implementation FECurrentWarringVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = FEString(@"CURRENT_WARRING");
    }
    return self;
}

//request alarm list
-(void)requestHistoryWarring{
    
    FEPage *page = [[FEPage alloc] initWithPageSize:0 currentPage:0 count:0];
    FEAttribute *attr = [[FEAttribute alloc] initWithAttrName:@"" value:@""];
    FEHistoryAlarmRequest *hdata = [[FEHistoryAlarmRequest alloc] initWithUserID:FELoginUser.userid page:page attributes:@[attr]];
    
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] historyAlarmList:hdata reponse:^(NSError *error, FEHistoryAlarmResponse *response) {
        if (!error && response.result.errorCode.integerValue == 0){
            [weakself.warringList removeAllObjects];
            [weakself.warringList addObjectsFromArray:response.alarmList];
            [weakself.tableView reloadData];
        }
    }];
    
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    _warringList = [NSMutableArray new];
    [self requestHistoryWarring];
}


#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    static NSString *identifier = @"cell";
    FEWarringTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
    if (!cell) {
        cell = [[FEWarringTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:identifier];
    }
    [cell configWithAlarm:_warringList[indexPath.row]];
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    
    return self.warringList.count;

}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 50;
}

#pragma mark - UITableViewDelegate
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    
    FEWarringDetailVC *response = [[FEWarringDetailVC alloc] initWithAlarm:_warringList[indexPath.row]];
    response.hidesBottomBarWhenPushed = YES;
    [self.navigationController pushViewController:response animated:YES];
    
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
