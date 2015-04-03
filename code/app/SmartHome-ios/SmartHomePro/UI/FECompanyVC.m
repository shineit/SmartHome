//
//  FECompanyVC.m
//  SmartHome
//
//  Created by Seven on 15-4-1.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECompanyVC.h"
#import "FECompanyListResponse.h"
#import "FEGetCompanyRequest.h"
#import "FEWebServiceManager.h"
#import "FEMemoryCache.h"

@interface FECompanyVC (){
    NSMutableArray *_companys;
}

@end

@implementation FECompanyVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"公司列表";
    _companys = [NSMutableArray new];
    [self requestCompany];
}

-(void)requestCompany{
    FEGetCompanyRequest *rdata = [[FEGetCompanyRequest alloc] initWithUid:[FEMemoryCache sharedInstance].user.userID page:[[FEPage alloc] initWithPageSize:0 currentPage:0 count:0]];
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FECompanyListResponse class] response:^(NSError *error, id response) {
        FECompanyListResponse *rsp = response;
        if (!error && rsp.result.errorCode.integerValue == 0) {
            [_companys addObjectsFromArray:rsp.companyList];
            [weakself.tableView reloadData];
        }
    }];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    FECompany *company = _companys[indexPath.row];
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"companyItemCell" forIndexPath:indexPath];
    cell.textLabel.text = company.applyName;
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return _companys.count;
}


#pragma mark - UITableViewDelegate
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    [self performSegueWithIdentifier:@"toWarringSegue" sender:self];
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
