//
//  FEEWarringVC.m
//  SmartHome
//
//  Created by Seven on 15-4-1.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEEWarringVC.h"
#import "FEFireAlarmByCidRequest.h"
#import "FEFireAlarmResponse.h"
#import "FEWebServiceManager.h"
#import "FEMemoryCache.h"
#import "FECompany.h"

@interface FEEWarringVC ()

@end

@implementation FEEWarringVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self requestAlarm];
}

-(void)requestAlarm{
    if (self.company) {
        FEFireAlarmByCidRequest *rdata = [[FEFireAlarmByCidRequest alloc] initWithUid:[FEMemoryCache sharedInstance].user.userID comanyId:self.company.companyID page:[[FEPage alloc] initWithPageSize:0 currentPage:0 count:0] filterList:nil];
        [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FEFireAlarmResponse class] response:^(NSError *error, id response) {
            FEFireAlarmResponse *rsp = response;
            if (!error && rsp.result.errorCode.integerValue == 0) {
                
            }
        }];
    }
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
