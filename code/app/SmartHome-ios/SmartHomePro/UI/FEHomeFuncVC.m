//
//  FEHomeVC.m
//  SmartHome
//
//  Created by Seven on 15-4-1.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEHomeFuncVC.h"
#import "FECommonDefine.h"
#import "FECompanyVC.h"
#import "FEFireAlarmNumRequest.h"
#import "FEDeviceStatusNumRequest.h"
#import "FECheckLogNumRequest.h"
#import "FENumberResponse.h"
#import "FEWebServiceManager.h"
#import "FEMemoryCache.h"
#import "FECustomerResponse.h"
#import "FECustomerRequest.h"

#define PNG_KEY @"png"
#define ITEM_TITLE   @"title"
#define ITEM_ACTION     @"action"


@interface FEHomeFuncVC ()<UICollectionViewDataSource,UICollectionViewDelegate,UICollectionViewDelegateFlowLayout>

@property (nonatomic, strong) NSArray *datasource;
@property (nonatomic, strong) NSArray *headerArray;
@property (strong, nonatomic) IBOutlet UICollectionView *funcCollectionView;
@property (strong, nonatomic) NSArray *alarmArray;
@property (strong, nonatomic) NSArray *statusArray;
@property (strong, nonatomic) NSArray *checkLogArray;

@end

@implementation FEHomeFuncVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = kString(@"首页");
    
    
    
    self.datasource =
    @[
      @[@{PNG_KEY:@"menu_alarm",ITEM_TITLE:kString(@"告警信息"),ITEM_ACTION:[self getIvocationWith:@selector(toWarring)]},
        @{PNG_KEY:@"menu_control",ITEM_TITLE:kString(@"设备状态"),ITEM_ACTION:[self getIvocationWith:@selector(toDeviceStatus)]},
        @{PNG_KEY:@"menu_area",ITEM_TITLE:kString(@"日常巡检"),ITEM_ACTION:[self getIvocationWith:@selector(toRoute)]},
        @{PNG_KEY:@"menu_account",ITEM_TITLE:kString(@"基本信息"),ITEM_ACTION:[self getIvocationWith:@selector(toInfo)]},
        @{PNG_KEY:@"menu_plane",ITEM_TITLE:kString(@"智慧管理"),ITEM_ACTION:[self getIvocationWith:@selector(toManage)]}],
      
      @[@{PNG_KEY:@"menu_note",ITEM_TITLE:kString(@"消防常识"),ITEM_ACTION:[self getIvocationWith:@selector(toKnowledge)]},
        @{PNG_KEY:@"menu_mall",ITEM_TITLE:kString(@"设备商城"),ITEM_ACTION:[self getIvocationWith:@selector(toStore)]}]
      ];
    self.headerArray = @[kString(@"智慧消防"),kString(@"其他信息")];
//    [self requestNumber];
    [self requestCustomer];
    __weak typeof(self) weakself = self;
    [[NSNotificationCenter defaultCenter] addObserverForName:kAlarmNotification object:nil queue:[NSOperationQueue mainQueue] usingBlock:^(NSNotification *note) {
        [weakself requestNumber];
    }];
    
}


-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    [self requestNumber];
}


-(void)requestNumber{
    __weak typeof(self) weakself = self;
    dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0);
    dispatch_group_t group = dispatch_group_create();
    dispatch_group_async(group, queue, ^{
        dispatch_semaphore_t sem = dispatch_semaphore_create(0);
        FEFireAlarmNumRequest *rdata = [[FEFireAlarmNumRequest alloc] initWithUid:[FEMemoryCache sharedInstance].user.userID];
        [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FENumberResponse class] response:^(NSError *error, id response) {
            FENumberResponse *rsp = response;
            if (!error && rsp.result.errorCode.integerValue == 0) {
                weakself.alarmArray = rsp.numList;
//                weakself.productNew = response.productList;
            }
            dispatch_semaphore_signal(sem);
        }];
        dispatch_semaphore_wait(sem, DISPATCH_TIME_FOREVER);
    });
    
    dispatch_group_async(group, queue, ^{
        dispatch_semaphore_t sem = dispatch_semaphore_create(0);
        FEDeviceStatusNumRequest *rdata = [[FEDeviceStatusNumRequest alloc] initWithUid:[FEMemoryCache sharedInstance].user.userID];
        [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FENumberResponse class] response:^(NSError *error, id response) {
            FENumberResponse *rsp = response;
            if (!error && rsp.result.errorCode.integerValue == 0) {
                weakself.statusArray = rsp.numList;
//                weak self.sellerList = response.sellerList;
            }
            dispatch_semaphore_signal(sem);
        }];
        dispatch_semaphore_wait(sem, DISPATCH_TIME_FOREVER);
    });
    
    dispatch_group_async(group, queue, ^{
        dispatch_semaphore_t sem = dispatch_semaphore_create(0);
        FECheckLogNumRequest *rdata = [[FECheckLogNumRequest alloc] initWithUid:[FEMemoryCache sharedInstance].user.userID];
        [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FENumberResponse class] response:^(NSError *error, id response) {
            FENumberResponse *rsp = response;
            if (!error && rsp.result.errorCode.integerValue == 0) {
                weakself.checkLogArray = rsp.numList;
//                weakself.productList = response.productList;
            }
            dispatch_semaphore_signal(sem);
        }];
        dispatch_semaphore_wait(sem, DISPATCH_TIME_FOREVER);
    });
    
    dispatch_group_notify(group, dispatch_get_main_queue(), ^{
//        _productRecommendBecome = YES;
//        [weakself.shopingTableView reloadData];
        [weakself.funcCollectionView reloadData];
    });
}

-(void)requestCustomer{
    FECustomerRequest *rdata = [[FECustomerRequest alloc] initWithUid:[FEMemoryCache sharedInstance].user.userID];
    [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FECustomerResponse class] response:^(NSError *error, id response) {
        FECustomerResponse *rsp = response;
        if (!error && rsp) {
            FECustomer *customer = rsp.customer;
            [FEMemoryCache sharedInstance].customer = customer;
        }
    }];
}

#pragma mark - UICollectionViewDataSource

// The cell that is returned must be retrieved from a call to -dequeueReusableCellWithReuseIdentifier:forIndexPath:
- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    
    UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"functionCell" forIndexPath:indexPath];
    UIImageView *imageView = (UIImageView *)[cell viewWithTag:1];
    imageView.image = [UIImage imageNamed:self.datasource[indexPath.section][indexPath.row][PNG_KEY]];
    UILabel *label = (UILabel *)[cell viewWithTag:2];
    label.text = self.datasource[indexPath.section][indexPath.row][ITEM_TITLE];
    
    UILabel *numberLabel = (UILabel *)[cell viewWithTag:3];
    numberLabel.layer.cornerRadius = numberLabel.bounds.size.width / 2.0f;
    numberLabel.layer.masksToBounds = YES;
    numberLabel.backgroundColor = [UIColor yellowColor];
    numberLabel.textColor = [UIColor redColor];
    if (indexPath.section == 0) {
        switch (indexPath.row) {
            case 0:{
                    NSInteger num = 0;
                    for (FEBageNumber *bage in self.alarmArray) {
                        num += bage.num.integerValue;
                    }
                    if (num) {
                        numberLabel.hidden = NO;
                        numberLabel.text = [NSString stringWithFormat:@"%lu",(unsigned long)num];
                    }else{
                        numberLabel.hidden = YES;
                    }
                }
                break;
            case 1:{
                NSInteger num = 0;
                for (FEBageNumber *bage in self.statusArray) {
                    num += bage.num.integerValue;
                }
                if (num) {
                    
                    numberLabel.hidden = NO;
                    numberLabel.text = [NSString stringWithFormat:@"%lu",(unsigned long)num];
                }else{
                    numberLabel.hidden = YES;
                }
            }
                break;
            case 4:{
                NSInteger num = 0;
                for (FEBageNumber *bage in self.checkLogArray) {
                    num += bage.num.integerValue;
                }
                if (num) {
                    numberLabel.hidden = NO;
                    numberLabel.text = [NSString stringWithFormat:@"%lu",(unsigned long)num];
                }else{
                    numberLabel.hidden = YES;
                }

            }
                break;
            default:
                numberLabel.hidden = YES;
                break;
        }
        if (indexPath.row == 0) {
            
        }
        if (indexPath.row == 1) {
            
        }
        if (indexPath.row == 4) {
            
        }
    }else{
        numberLabel.hidden = YES;
    }
    
    return cell;
}

-(NSInteger)numberOfSectionsInCollectionView:(UICollectionView *)collectionView
{
    return self.datasource.count;
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{
    return [(NSArray *)self.datasource[section] count];
}

#pragma mark - UICollectionViewDelegateFlowLayout


- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout referenceSizeForHeaderInSection:(NSInteger)section{
    return CGSizeMake(self.view.bounds.size.width, 40);
}

-(UICollectionReusableView *)collectionView:(UICollectionView *)collectionView viewForSupplementaryElementOfKind:(NSString *)kind atIndexPath:(NSIndexPath *)indexPath{
    UICollectionReusableView * reusableview = nil ;
    
    if ( kind == UICollectionElementKindSectionHeader ) {
        UICollectionReusableView *view = [collectionView dequeueReusableSupplementaryViewOfKind:UICollectionElementKindSectionHeader withReuseIdentifier:@"headerView" forIndexPath:indexPath];
        UILabel *tLabel = (UILabel *)[view viewWithTag:1];
        tLabel.text = self.headerArray[indexPath.section];;
        return view;
    }
    return reusableview;
}

#pragma mark - UICollectionViewDelegate
-(void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath{
    
    
    NSInvocation *iv = self.datasource[indexPath.section][indexPath.row][ITEM_ACTION];
    [iv invoke];
}

-(void)toWarring{
    [self performSegueWithIdentifier:@"toCompanySegue" sender:@(FIRE_ALARM)];
}

-(void)toDeviceStatus{
    [self performSegueWithIdentifier:@"toCompanySegue" sender:@(DEVICE_STATUS)];
}

-(void)toRoute{
    [self performSegueWithIdentifier:@"toCompanySegue" sender:@(CHECK)];
}

-(void)toInfo{
    [self performSegueWithIdentifier:@"toCompanySegue" sender:@(INFO)];
}

-(void)toManage{
    [self performSegueWithIdentifier:@"toCompanySegue" sender:@(MANAGE)];
}

-(void)toKnowledge{
    [self performSegueWithIdentifier:@"toFireKnowledgeSegue" sender:self];
}

-(void)toStore{
    [self performSegueWithIdentifier:@"toStoreSegue" sender:self];
}

-(NSInvocation *)getIvocationWith:(SEL)selector{
    NSMethodSignature *sig=[self methodSignatureForSelector:selector];
    NSInvocation *invocation = [NSInvocation invocationWithMethodSignature:sig];
    [invocation setSelector:selector];
    [invocation setTarget:self];
    return invocation;
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
    if ([segue.identifier isEqualToString:@"toCompanySegue"]) {
        FECompanyVC *vc = segue.destinationViewController;
        vc.type = [sender integerValue];
        if (vc.type == FIRE_ALARM) {
            vc.numbers = self.alarmArray;
        }else if (vc.type == DEVICE_STATUS){
            vc.numbers = self.statusArray;
        }else if(vc.type == MANAGE){
            vc.numbers = self.checkLogArray;
        }
    }
}


@end
