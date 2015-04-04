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
#import "FECheckLogByIdRequest.h"
#import "FECheckLogByIdResponse.h"
#include "FEWebServiceManager.h"
#import "FECompany.h"
#import "FEMemoryCache.h"

@interface FECheckListVC (){
    NSMutableArray *_checkLogList;
}

@end

@implementation FECheckListVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    _checkLogList = [NSMutableArray new];
    [self requestCheckList];
}

-(void)requestCheckList{
    
    FECheckLogByIdRequest *rdata = [[FECheckLogByIdRequest alloc] initWithUid:[FEMemoryCache sharedInstance].user.userID comanyId:self.company.companyID page:[[FEPage alloc] initWithPageSize:0 currentPage:0 count:0] filterList:nil];
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FECheckLogByIdResponse class] response:^(NSError *error, id response) {
        FECheckLogByIdResponse *rsp = response;
        if (!error && rsp.result.errorCode.integerValue == 0) {
            [_checkLogList addObjectsFromArray:rsp.checkLogList];
            [weakself.tableView reloadData];
        }
        
    }];
    
    
//    FECheckListRequest *rdata = [[FECheckListRequest alloc] initWithCid:self.company.companyID];
//    [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FECheckListResponse class] response:^(NSError *error, id response) {
//        FECheckListResponse *rsp = response;
//        if (!error && rsp.result.errorCode.integerValue == 0) {
//            
//        }
//    }];
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"checkLogCell" forIndexPath:indexPath];
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return _checkLogList.count;
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
