//
//  FEKnowLedgeListVC.m
//  SmartHome
//
//  Created by Seven on 15-4-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEKnowLedgeListVC.h"
#import "FEWebServiceManager.h"
#import "FEKnowLedgeListRequest.h"
#import "FEKnowledgeListResponse.h"
#import "FEMemoryCache.h"
#import "FEKnowLedgeDetailVC.h"

@interface FEKnowLedgeListVC (){
    NSMutableArray *_knowLedgeList;
}

@end

@implementation FEKnowLedgeListVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"消防常识";
    _knowLedgeList = [NSMutableArray new];
    [self requestKnowLedge];
}

-(void)requestKnowLedge{
    FEKnowLedgeListRequest *rdata = [[FEKnowLedgeListRequest alloc] initWithUid:[FEMemoryCache sharedInstance].user.userID page:[[FEPage alloc] initWithPageSize:0 currentPage:0 count:0] fillter:nil];
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FEKnowledgeListResponse class] response:^(NSError *error, id response) {
        FEKnowledgeListResponse *rsp = response;
        if (!error && rsp.result.errorCode.integerValue == 0) {
            [_knowLedgeList addObjectsFromArray:rsp.knowledgeList];
            [weakself.tableView reloadData];
        }
    }];
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    FEKnowledge *knowledge = _knowLedgeList[indexPath.row];
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"knowLedgeItemCell" forIndexPath:indexPath];
    cell.textLabel.text = knowledge.title;
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return _knowLedgeList.count;
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
    NSInteger index = [self.tableView indexPathForCell:sender].row;
    FEKnowLedgeDetailVC *vc = segue.destinationViewController;
    vc.knowledge = _knowLedgeList[index];
}


@end
