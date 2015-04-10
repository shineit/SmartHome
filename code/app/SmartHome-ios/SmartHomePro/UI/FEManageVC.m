//
//  FEManageVC.m
//  SmartHome
//
//  Created by Seven on 15-4-5.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEManageVC.h"
#import "FECheckLogByIdRequest.h"
#import "FECheckLogByIdResponse.h"
#import "FEWebServiceManager.h"
#import "FEMemoryCache.h"
#import "FECompany.h"

@interface FEManageVC (){
    NSMutableArray *_checkLog;
}

@end

@implementation FEManageVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"智慧管理";
    _checkLog = [NSMutableArray new];
    [self requestLog];
}

-(void)requestLog{
    FECheckLogByIdRequest *rdata = [[FECheckLogByIdRequest alloc] initWithUid:[FEMemoryCache sharedInstance].user.userID comanyId:self.company.companyID page:[[FEPage alloc] initWithPageSize:0 currentPage:0 count:0] filterList:nil];
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FECheckLogByIdResponse class] response:^(NSError *error, id response) {
        FECheckLogByIdResponse *rsp = response;
        if (!error && rsp.result.errorCode.integerValue == 0) {
            [_checkLog addObjectsFromArray:rsp.checkLogList];
            [weakself.tableView reloadData];
        }
    }];
    
}


#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    FECheckLog *log = _checkLog[indexPath.row];
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"manageCell" forIndexPath:indexPath];
    UILabel *title = (UILabel *)[cell viewWithTag:1];
    UILabel *detail = (UILabel *)[cell viewWithTag:2];
    UIImageView *imageView = (UIImageView *)[cell viewWithTag:3];
    UILabel *status = (UILabel *)[cell viewWithTag:4];
    if (log.abnormalPic) {
        imageView.hidden = NO;
    }else{
        imageView.hidden = YES;
    }
    title.text = log.checkItem;
    detail.text = log.checkSys;
    status.text = log.checkResult.stringValue;
    
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return _checkLog.count;
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
