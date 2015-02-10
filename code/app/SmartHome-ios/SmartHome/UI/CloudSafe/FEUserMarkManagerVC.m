//
//  FEUserMarkManagerVC.m
//  SmartHome
//
//  Created by Seven on 14-12-29.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEUserMarkManagerVC.h"
#import "FEDevicesCache.h"
#import "FEUserMark.h"
#import "FEMarkAddPopView.h"
#import "FEMarkSetRequest.h"
#import "AppDelegate.h"
#import "FEWebServiceManager.h"
#import "CDUser.h"
#import "FECoreDataHandler.h"
#import "FEBaseResponse.h"
#import "FEMarkDeletRequest.h"

@interface FEUserMarkManagerVC ()<UITableViewDataSource,UITableViewDelegate,CustomIOS7AlertViewDelegate>

@property (nonatomic, strong) FETableView *markTableView;
@property (nonatomic, strong) NSArray *marks;

@end

@implementation FEUserMarkManagerVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self initUI];
    __weak typeof(self) weakself = self;
    [[FEDevicesCache sharedInstance] getAllMarks:^(NSArray *items) {
        weakself.marks = items;
        [self.markTableView reloadData];
    }];
}

-(void)initUI{
    [self loadRightCustomButtonItemWithTitle:FEString(@"MAEK_ADD") image:nil];
    
    self.title = FEString(@"MARK_TITLE");
    _markTableView = [[FETableView alloc] initWithFrame:self.view.bounds];
    _markTableView.dataSource = self;
    _markTableView.delegate = self;
    [self.view addSubview:_markTableView];
    
}

-(void)rightbarpressed:(UIButton *)button{
    FEMarkAddPopView *popview = [[FEMarkAddPopView alloc] initWithTitle:FEString(@"New mark") Titles:@[FEString(@"CANCEL"),FEString(@"OK")]];
    popview.delegate = self;
    [popview show];
    
}

#pragma mark - CustomIOS7AlertViewDelegate
-(void)customIOS7dialogButtonTouchUpInside:(id)alertView clickedButtonAtIndex:(NSInteger)buttonIndex{
    if (buttonIndex == 1) {
        NSString *string = ((FEMarkAddPopView *)alertView).textField.text;
        if (string.length != 0) {
            [self addMark:string];
        }
    }
    [alertView close];
}

-(void)addMark:(NSString *)str{
    [self displayHUD:FEString(@"LOADING...")];
    __weak typeof(self) weakself = self;
    FEUserMark *mark = [[FEUserMark alloc] initWithUserID:FELoginUser.userid mark:str];
    FEMarkSetRequest *markRequest = [[FEMarkSetRequest alloc] initWithMark:mark];
    [[FEWebServiceManager sharedInstance] markSet:markRequest response:^(NSError *error, FEBaseResponse *response) {
        [self hideHUD:YES];
        if (!error && response.result.errorCode.integerValue == 0) {
            NSLog(@"add mark success!");
            [[FEDevicesCache sharedInstance] addMark:mark];
            [[FEDevicesCache sharedInstance] getAlldevices:^(NSArray *items) {
                weakself.marks = items;
                [weakself.markTableView reloadData];
                [[NSNotificationCenter defaultCenter] postNotificationName:FEMarkDidChangeNotification object:nil];
            }];
        }
        [weakself hideHUD:YES];
    }];
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    static NSString *identifier = @"cell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
    if (!cell) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:identifier];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        FEButton *btn = [FEButton buttonWithType:UIButtonTypeCustom];
        [btn addTarget:self action:@selector(deleteMark:) forControlEvents:UIControlEventTouchUpInside];
        [btn setTitle:FEString(@"MARK_DELETE") forState:UIControlStateNormal];
        btn.frame = CGRectMake(0, 0, 80, 30);
        cell.accessoryView = btn;
        
    }
    FEUserMark *mark = self.marks[indexPath.row];
    if ([mark.mark isEqualToString:@"未分组"]) {
        cell.accessoryView.hidden = YES;
    }else{
        cell.accessoryView.hidden = NO;
    }
    cell.textLabel.text = mark.mark;
    return cell;
    
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.marks.count;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 60;
}

-(void)deleteMark:(UIButton *)button{
    UITableViewCell *cell = (UITableViewCell *)button.superview;
    NSIndexPath *indexPath = [self.markTableView indexPathForCell:cell];
    FEUserMark *mark = [self.marks objectAtIndex:indexPath.row];
    [self deleteMarkRequest:mark];
    
}

-(void)deleteMarkRequest:(FEUserMark *)mark{
    __weak typeof(self) weakself = self;
    [self displayHUD:FEString(@"LOADING...")];
    FEMarkDeletRequest *rdata = [[FEMarkDeletRequest alloc] initWithMark:mark];
    [[FEWebServiceManager sharedInstance] markDelete:rdata response:^(NSError *error, FEBaseResponse *response) {
        if (!error && response.result.errorCode.integerValue == 0) {
            [[FEDevicesCache sharedInstance] removeMark:mark];
            [[FEDevicesCache sharedInstance] getAllMarks:^(NSArray *items) {
                weakself.marks = items;
                [weakself.markTableView reloadData];
            }];
            [[NSNotificationCenter defaultCenter] postNotificationName:FEMarkDidChangeNotification object:nil];
        }
        [weakself hideHUD:YES];
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
