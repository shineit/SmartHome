//
//  FECheckListVC.m
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECheckListVC.h"
#import "FECheckListRequest.h"
#import "FECheckListResponse.h"
#include "FEWebServiceManager.h"
#import "FECompany.h"

@interface FECheckListVC ()

@end

@implementation FECheckListVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self requestCheckList];
}

-(void)requestCheckList{
    FECheckListRequest *rdata = [[FECheckListRequest alloc] initWithCid:self.company.companyID];
    [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FECheckListResponse class] response:^(NSError *error, id response) {
        FECheckListResponse *rsp = response;
        if (!error && rsp.result.errorCode.integerValue == 0) {
            
        }
    }];
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
