//
//  FEMallProductListVC.m
//  SmartHome
//
//  Created by Seven on 15-4-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//


#import "FEMallProductListVC.h"
#import "FEWebServiceManager.h"
#import "FEMallProductListRequest.h"
#import "FEMallProductResponse.h"
#import "FEMemoryCache.h"
#import "FEADRequest.h"
#import "FEADResponse.h"
#import <SDWebImage/UIImageView+WebCache.h>
#import "Define.h"
#import "FEProductDetailVC.h"

@interface FEMallProductListVC ()<UICollectionViewDataSource,UICollectionViewDelegate,UICollectionViewDelegateFlowLayout>{
    NSMutableArray *_products;
    NSArray *_adList;
    BOOL _runing;
}
@property (strong, nonatomic) IBOutlet UICollectionView *productCollectionView;
@property (strong, nonatomic) IBOutlet UIScrollView *adScrollView;
@property (strong, nonatomic) IBOutlet UIPageControl *pageIndicate;

@end

@implementation FEMallProductListVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"设备商城";
    self.pageIndicate.numberOfPages = 0;
    _products = [NSMutableArray new];
    _runing = YES;
    [self requestAD];
    [self requestProduct];
    
}

-(void)dealloc{
    _runing = NO;
}

-(void)requestProduct{
    FEMallProductListRequest *rdata = [[FEMallProductListRequest alloc] initWithUid:[FEMemoryCache sharedInstance].user.userID page:[[FEPage alloc] initWithPageSize:0 currentPage:0 count:0] fillter:nil];
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FEMallProductResponse class] response:^(NSError *error, id response) {
        FEMallProductResponse *rsp = response;
        if (!error && rsp.result.errorCode.integerValue == 0) {
            [_products addObjectsFromArray:rsp.productList];
            [weakself.productCollectionView reloadData];
        }
    }];
    
}

-(void)requestAD{
    FEADRequest *rdata = [[FEADRequest alloc] initWithUid:[FEMemoryCache sharedInstance].user.userID page:[[FEPage alloc] initWithPageSize:0 currentPage:0 count:0] filter:nil];
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FEADResponse class] response:^(NSError *error, id response) {
        FEADResponse *rsp = response;
        if (!error && rsp.result.errorCode.integerValue == 0) {
            int i = 0;
            weakself.adScrollView.contentSize = CGSizeMake(weakself.adScrollView.bounds.size.width * rsp.adList.count, weakself.adScrollView.bounds.size.height);
            for (FEAdvertisement *ad in rsp.adList) {
                UIImageView *imageView = [[UIImageView alloc] initWithFrame:CGRectMake(0 + i*weakself.adScrollView.bounds.size.width, 0, weakself.adScrollView.bounds.size.width, weakself.adScrollView.bounds.size.height)];
               
                [imageView sd_setImageWithURL:[NSURL URLWithString:kImageURL(ad.adImg)]];
                [weakself.adScrollView addSubview:imageView];
                i++;
            }
            _adList = rsp.adList;
            weakself.pageIndicate.numberOfPages = rsp.adList.count;
            [weakself autoScroll];
        }
    }];
}

-(void)autoScroll{
    __weak typeof(self) weakself = self;
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_LOW, 0), ^{
        NSInteger index = 0;
        while (_runing) {
            if (_adList && weakself.pageIndicate.numberOfPages != 0) {
                dispatch_async(dispatch_get_main_queue(), ^{
                    [weakself.adScrollView scrollRectToVisible:CGRectMake(index*weakself.adScrollView.bounds.size.width, 0, weakself.adScrollView.bounds.size.width, 10) animated:YES];
                    weakself.pageIndicate.currentPage = index;
                });
                index++;
                if (index >= _adList.count) {
                    index = 0;
                }
            }
            
            sleep(3);
        }
    });
}

#pragma mark - UICollectionViewDataSource
-(UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    
    FEProduct *product = _products[indexPath.row];
    UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"productCell" forIndexPath:indexPath];
    UIImageView *imageView = (UIImageView *)[cell viewWithTag:1];
//    imageView.backgroundColor = [UIColor redColor];
    [imageView sd_setImageWithURL:[NSURL URLWithString:kImageURL(product.picLabel)]];
    UILabel *pLabel = (UILabel *)[cell viewWithTag:2];
    pLabel.text = product.name;
    
    return cell;
}

-(UICollectionReusableView *)collectionView:(UICollectionView *)collectionView viewForSupplementaryElementOfKind:(NSString *)kind atIndexPath:(NSIndexPath *)indexPath{
    UICollectionReusableView *rView = nil;
    if (kind == UICollectionElementKindSectionHeader) {
        UICollectionReusableView *view = [collectionView dequeueReusableSupplementaryViewOfKind:UICollectionElementKindSectionHeader withReuseIdentifier:@"headerView" forIndexPath:indexPath];
        self.adScrollView = (UIScrollView *)[view viewWithTag:1];
        self.pageIndicate = (UIPageControl *)[view viewWithTag:2];
        
        return view;
    }
    return rView;
}

-(NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{
    return _products.count;
}

#pragma mark - UIScrollViewDelegate
-(void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView{
    if (scrollView == self.adScrollView) {
        NSInteger page = scrollView.contentOffset.x / scrollView.bounds.size.width;
        self.pageIndicate.currentPage = page;
    }
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
    NSIndexPath *indexPath = [self.productCollectionView indexPathForCell:sender];
    FEProduct *product = _products[indexPath.row];
    FEProductDetailVC *vc = segue.destinationViewController;
    vc.product = product;
}


@end
