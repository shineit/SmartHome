//
//  FECloudSafeVC.m
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECloudSafeVC.h"
#import "FEControlViwe.h"

@interface FECloudSafeVC ()<UITableViewDelegate,UITableViewDataSource>

@property (nonatomic, strong) UITableView *deviceTable;
@property (nonatomic, strong) NSMutableArray *deviceList;

@end

@implementation FECloudSafeVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = FEString(@"SAFE");
        _deviceList = [NSMutableArray arrayWithArray:@[@[@"烟雾报警器1",@"烟雾报警器2"],@[@"温度报警器1",@"温度报警器2",@"温度报警器3"]]];
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
    [self loadRightCustomButtonItemWithTitle:FEString(@"SEARCH") image:nil];
    
    FEControlViwe *cview = [[FEControlViwe alloc] initWithFrame:CGRectMake(0, 0, self.view.frame.size.width, 40)];
    [self.view addSubview:cview];
    _deviceTable = [[UITableView alloc] initWithFrame:CGRectMake(0, cview.frame.origin.y + cview.frame.size.height, self.view.bounds.size.width, self.view.bounds.size.height - cview.frame.size.height) style:UITableViewStyleGrouped];
    _deviceTable.dataSource = self;
    _deviceTable.delegate = self;
    _deviceTable.tableFooterView = [[UIView alloc] initWithFrame:CGRectZero];
    _deviceTable.tableHeaderView = [[UIView alloc] initWithFrame:CGRectZero];
    _deviceTable.autoresizingMask = UIViewAutoresizingFlexibleHeight;
    
    [self.view addSubview:_deviceTable];
}

-(void)rightbarpressed:(UIButton *)button{
    NSLog(@"search!");
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    static NSString *identifier = @"cell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
    if (!cell) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:identifier];
        
    }
    cell.textLabel.text = _deviceList[indexPath.section][indexPath.row];
    return cell;
    
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return [_deviceList[section] count];
}

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return _deviceList.count;
}

- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section{
    return @"卧室";
}


//-(NSInteger)tableView:(UITableView *)tableView sectionForSectionIndexTitle:(NSString *)title atIndex:(NSInteger)index{
//    
//}


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
