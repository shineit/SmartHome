//
//  FECheckListVC.m
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECheckListVC.h"
//#import "FECheckListRequest.h"
//#import "FECheckListResponse.h"
#import "FECheckListRequest.h"
#import "FECheckListResponse.h"

#import "FECheckLogByIdRequest.h"
#import "FECheckLogByIdResponse.h"
#include "FEWebServiceManager.h"
#import "FECompany.h"
#import "FEMemoryCache.h"
#import "FECheckOperationVC.h"
#import "FECheckLogCreateRequest.h"



@interface FECheckListVC (){
    NSMutableArray *_checkList;
    NSMutableArray *_checkLogs;
}
@property (strong, nonatomic) IBOutlet UITableView *tableView;

@end

@implementation FECheckListVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"日常巡检";
    _checkList = [NSMutableArray new];
    _checkLogs = [NSMutableArray new];
    [self requestCheckList];
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    [self.tableView reloadData];
}

-(void)requestCheckList{
    
    FECheckListRequest *rdata = [[FECheckListRequest alloc] initWithCid:@(0)];
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FECheckListResponse class] response:^(NSError *error, id response) {
        FECheckListResponse *rsp = response;
        if (!error && rsp.result.errorCode.integerValue == 0) {
            [_checkList addObjectsFromArray:rsp.checkItemList];
            [weakself.tableView reloadData];

        }
    }];
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    FECheckItem *check = _checkList[indexPath.row];
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"checkItemCell" forIndexPath:indexPath];
    UILabel *title = (UILabel *)[cell viewWithTag:1];
    UILabel *detail = (UILabel *)[cell viewWithTag:2];
    UILabel *operation = (UILabel *)[cell viewWithTag:3];
    for (FECheckLog *checkLog in _checkLogs) {
        if (checkLog.checkItemID.integerValue == check.itemID.integerValue) {
            if (checkLog.checkResult.integerValue == 0) {
                operation.text = @"未设置";
            }else if (checkLog.checkItemID.integerValue == 1){
                operation.text = @"正常";
            }else{
                operation.text = @"异常";
            }
            break;
        }
    }
    title.text = check.itemName;
    detail.text = check.itemSys;
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return _checkList.count;
}

- (IBAction)submit:(id)sender {
    if (_checkLogs.count) {
        __weak typeof(self) weakself = self;
        [weakself displayHUD:@"提交中..."];
        FECheckLogCreateRequest *rdata = [[FECheckLogCreateRequest alloc] initWithCheckLogList:_checkLogs];
        [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FEBaseResponse class] response:^(NSError *error, id response) {
            FEBaseResponse *rsp = response;
            if (!error && rsp.result.errorCode.integerValue == 0) {
                [weakself.navigationController popViewControllerAnimated:YES];
            }
            [weakself hideHUD:YES];
        }];
    }else{
        
    }
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
//    UITableViewCell *cell = sender;
    NSIndexPath *indexPath = [self.tableView indexPathForCell:sender];
    FECheckItem *check = _checkList[indexPath.row];
    FECheckOperationVC *vc = segue.destinationViewController;
    vc.company = self.company;
    vc.checkItem = check;
    vc.checkLogs = _checkLogs;
}


@end
