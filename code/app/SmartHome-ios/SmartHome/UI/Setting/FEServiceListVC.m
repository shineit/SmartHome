//
//  FEServiceListVC.m
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEServiceListVC.h"
#import "FEServiceTableViewCell.h"
#import "FEServiceRequestVC.h"

@interface FEServiceListVC ()<UITableViewDataSource,UITableViewDelegate>

@property (nonatomic, strong) UITableView *serviceTable;
@property (nonatomic, strong) NSMutableArray *serviceDatas;

@end

@implementation FEServiceListVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = FEString(@"SERVICE");
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self initUI];
}

-(void)initUI{
    
    [self loadRightCustomButtonItemWithTitle:FEString(@"ADD") image:nil];
    
    UITableView *table = [[UITableView alloc] initWithFrame:self.view.bounds style:UITableViewStylePlain];
    table.delegate = self;
    table.dataSource = self;
    [self.view addSubview:table];
    self.serviceTable = table;
    
    UIView *view = [UIView new];
    view.backgroundColor = [UIColor clearColor];
    [table setTableFooterView:view];

}

-(void)rightbarpressed:(UIButton *)button{
//    NSLog(@"add service!");
    FEServiceRequestVC *serviceRequest = [FEServiceRequestVC new];
    [self.navigationController pushViewController:serviceRequest animated:YES];
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    static NSString *identifier = @"cell";
    FEServiceTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
    if (!cell) {
        cell = [[FEServiceTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:identifier];
    }
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return 4;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 50;
}

-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    return 50;
}

-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    UIView *header = [[UIView alloc] initWithFrame:CGRectMake(0, 0, self.view.bounds.size.width, 50)];
    header.backgroundColor = [UIColor whiteColor];
    FELabel *label = [[FELabel alloc] initWithFrame:CGRectMake(5, 20, 60, 30)];
    label.text = FEString(@"ORDER");
    [header addSubview:label];
    
    label = [[FELabel alloc] initWithFrame:CGRectMake(100, 20, 80, 30)];
    label.text = FEString(@"TYPE");
    [header addSubview:label];
    
    label = [[FELabel alloc] initWithFrame:CGRectMake(240, 20, 80, 30)];
    label.text = FEString(@"STATUS");
    [header addSubview:label];
    return header;
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
